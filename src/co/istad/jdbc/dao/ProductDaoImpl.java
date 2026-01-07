package co.istad.jdbc.dao;

import co.istad.jdbc.config.DbConfig;
import co.istad.jdbc.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private final Connection conn;

    public ProductDaoImpl() {
        // Use your existing connection initialization
        conn = DbConfig.getInstance();
    }

    @Override
    public void deleteByCode(String code) {
        // Changed "is_deleted" logic: Usually we do a Soft Delete (setting is_deleted to true)
        // If you want to permanently delete, use: "DELETE FROM products WHERE code = ?"
        String sql = "DELETE FROM products WHERE code = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Could not delete product: " + e.getMessage());
        }
    }

    @Override
    public List<Product> searchByName(String name) {
        // Using 'ILIKE' for PostgreSQL (case-insensitive) or 'LIKE' for others
        String sql = "SELECT * FROM products WHERE name ILIKE ?";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Using your specific column names from the findAll() method
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setCode(rs.getString("code"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setQty(rs.getInt("qty"));
                product.setDeleted(rs.getBoolean("is_deleted"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return products;
    }

    @Override
    public int save(Product product) throws SQLException {
        final String SQL = """
                INSERT INTO products(code, name, price, qty, is_deleted)
                VALUES (?, ?, ?, ?, ?);
                """;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, product.getCode());
            pstmt.setString(2, product.getName());
            pstmt.setBigDecimal(3, product.getPrice());
            pstmt.setInt(4, product.getQty());
            pstmt.setBoolean(5, product.getDeleted());
            return pstmt.executeUpdate();
        }
    }

    @Override
    public List<Product> findAll() throws SQLException {
        final String SQL = "SELECT * FROM products";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {

            List<Product> productList = new ArrayList<>();
            while(rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setCode(rs.getString("code"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setQty(rs.getInt("qty"));
                product.setDeleted(rs.getBoolean("is_deleted"));
                productList.add(product);
            }
            return productList;
        }
    }
}