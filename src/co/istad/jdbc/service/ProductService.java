package co.istad.jdbc.service;

import co.istad.jdbc.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
}
