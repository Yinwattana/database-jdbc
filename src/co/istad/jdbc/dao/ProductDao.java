package co.istad.jdbc.dao;

import co.istad.jdbc.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    List<Product> findAll() throws SQLException;

}
