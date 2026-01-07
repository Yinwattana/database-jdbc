package co.istad.jdbc;


import co.istad.jdbc.config.DbConfig;
import co.istad.jdbc.model.Product;
import co.istad.jdbc.service.ProductService;
import co.istad.jdbc.service.ProductServiceImpl;
import co.istad.jdbc.util.InputUtil;
import co.istad.jdbc.util.ViewUtil;

import java.util.List;

public class JdbcProgram {
    public static void main(String[] args) {

        DbConfig.init();

        ProductService productService = new ProductServiceImpl();

        do {
            ViewUtil.printMenu();

            int option = InputUtil.getInteger("Enter menu");
            switch (option) {
                case 0 -> System.exit(0);
                case 1 -> {
                    List<Product> productList = productService.findAll();
                    ViewUtil.printProductList(productList);
                }
                case 2 -> System.out.println("Search");
                case 3 -> System.out.println("Invalid menu..");

            }
        }while (true);
    }
}