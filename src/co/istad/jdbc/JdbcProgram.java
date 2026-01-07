package co.istad.jdbc;


import co.istad.jdbc.config.DbConfig;
import co.istad.jdbc.model.Product;
import co.istad.jdbc.service.ProductService;
import co.istad.jdbc.service.ProductServiceImpl;
import co.istad.jdbc.util.InputUtil;
import co.istad.jdbc.util.ViewUtil;

import java.math.BigDecimal;
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
                    try{
                        List<Product> productList = productService.findAll();
                        ViewUtil.printProductList(productList);
                    }catch(RuntimeException e){
                        ViewUtil.printHeader(e.getMessage());
                    }
                }
                case 2 -> System.out.println("Search");
                case 3 -> {
                    String code = InputUtil.getText("Enter code");
                    String name = InputUtil.getText("Enter name");
                    BigDecimal price = InputUtil.getMoney("Enter price");
                    Integer qty = InputUtil.getInteger("Enter quantity");
                    Product product = new Product(code,name,price,qty,true  );

                    try {
                        productService.save(product);
                        ViewUtil.printHeader("Product has been saved successfully..");
                    }catch(RuntimeException e){
                        ViewUtil.printHeader(e.getMessage());
                    }
                }

            }
        }while (true);
    }
}