package co.istad.jdbc.dao;

import co.istad.jdbc.config.DbConfig;
import co.istad.jdbc.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private final Connection conn;

    public ProductDaoImpl() {
        conn = DbConfig.getInstance();
    }

    @Override
    public int save(Product product) throws SQLException {

        final String SQL = """
                INSERT INTO products(code, name, price, qty, is_deleted)
                VALUES (?, ?, ?, ?, ?);
                """;
        PreparedStatement pstmt = conn.prepareStatement(SQL);

        pstmt.setString(1, product.getCode());
        pstmt.setString(2, product.getName());
        pstmt.setBigDecimal(3, product.getPrice());
        pstmt.setInt(4, product.getQty());
        pstmt.setBoolean(5, product.getDeleted());



        return pstmt.executeUpdate();
    }

    @Override
    public List<Product> findAll() throws SQLException {
        Statement stmt = conn.createStatement();

        final String SQL = """
                SELECT *
                FROM products
                """;
        ResultSet rs = stmt.executeQuery(SQL);

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
