package co.istad.jdbc.dao;

import co.istad.jdbc.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    int save(Product product) throws SQLException;

    // new code AI
    void deleteByCode(String code);
    void updateByCode(String code, Product product);
    // end
    List<Product> searchByName(String name);

    List<Product> findAll() throws SQLException;

}
