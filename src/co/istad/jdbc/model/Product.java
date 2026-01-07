package co.istad.jdbc.model;

import java.math.BigDecimal;

public class Product {
    private Integer id;
    private String code;
    private String name;
    private BigDecimal price;
    private Integer qty;
    private Boolean isDeletd;

    public Product(){}

    public Product(String code, String name, BigDecimal price, Integer qty, Boolean isDeletd) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.isDeletd = isDeletd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Boolean getDeletd() {
        return isDeletd;
    }

    public void setDeletd(Boolean deletd) {
        isDeletd = deletd;
    }

    public Object getDeleted() {
        return null;
    }
}
