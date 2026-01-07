package co.istad.jdbc.service;

import co.istad.jdbc.dao.ProductDao;
import co.istad.jdbc.dao.ProductDaoImpl;
import co.istad.jdbc.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    // 1. Keep this declaration and constructor
    private final ProductDao productDao;

    public ProductServiceImpl() {
        productDao = new ProductDaoImpl();
    }

    @Override
    public void save(Product product) {
        try {
            int affectedRow = productDao.save(product);
            if (affectedRow > 1)
                throw new RuntimeException("Save operation failed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAll() {
        try {
            return productDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 2. I removed the duplicate "private ProductDao productDao..." line that was here

    @Override
    public void deleteByCode(String code) {
        productDao.deleteByCode(code);
    }

    @Override
    public List<Product> searchByName(String name) {
        return productDao.searchByName(name);
    }
    // add update
    @Override
    public void updateByCode(String code, Product product) {
        productDao.updateByCode(code, product);
    }
    // end
}