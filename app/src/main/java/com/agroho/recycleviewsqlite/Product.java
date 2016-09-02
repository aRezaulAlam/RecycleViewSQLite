package com.agroho.recycleviewsqlite;

/**
 * Created by rezau on 2016-08-31.
 */
public class Product {

    private Long productId;
    private String productName;
    private String productCategory;
    private String productLocation;
    private int productPrice;

    public Product(){

    }

    public Product(String productName, String productCategory, String productLocation, int productPrice) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productLocation = productLocation;
        this.productPrice = productPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}
