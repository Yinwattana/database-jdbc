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

                //AI case 2
                case 2 -> {
                    // SEARCH LOGIC
                    String name = InputUtil.getText("Enter name to search");
                    List<Product> results = productService.searchByName(name);
                    ViewUtil.printProductList(results);
                }
                // end case 2
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
                //Ai case 4
                case 4 -> {
                    String code = InputUtil.getText("Enter product code to delete");
                    try {
                        productService.deleteByCode(code);
                        ViewUtil.printHeader("Product deleted successfully!");
                    } catch (RuntimeException e) {
                        ViewUtil.printHeader(e.getMessage());
                    }
                }
                // end case 4
                case 5 -> {
                    // 1. Ask for the ID/Code of the product to update
                    String oldCode = InputUtil.getText("Enter the Product Code you want to UPDATE");

                    // 2. Collect the new information
                    String newName = InputUtil.getText("Enter new name");
                    BigDecimal newPrice = InputUtil.getMoney("Enter new price");
                    Integer newQty = InputUtil.getInteger("Enter new quantity");

                    // 3. Create the updated Product object (assuming status is active/false for deleted)
                    Product updatedProduct = new Product(oldCode, newName, newPrice, newQty, false);

                    try {
                        productService.updateByCode(oldCode, updatedProduct);
                        ViewUtil.printHeader("Product updated successfully!");
                    } catch (RuntimeException e) {
                        ViewUtil.printHeader(e.getMessage());
                    }
                }
                //end case5

            }
        }while (true);
    }
}