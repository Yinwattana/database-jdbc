package co.istad.jdbc.service;

import co.istad.jdbc.model.Product;

import java.util.List;

public interface ProductService {

    void save(Product product);
    //code AI
    void deleteByCode(String code);
    void updateByCode(String code, Product product);
    //end
    List<Product> searchByName(String name);

    List<Product> findAll();
}
