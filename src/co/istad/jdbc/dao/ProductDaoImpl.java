package co.istad.jdbc.dao;

import co.istad.jdbc.config.DbConfig;
import co.istad.jdbc.model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private final Connection conn;

    public ProductDaoImpl() {
        conn = DbConfig.getInstance();
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
