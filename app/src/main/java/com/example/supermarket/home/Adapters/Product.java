package com.example.supermarket.home.Adapters;

public class Product {
    String productName;
    int productImgId;

    public Product(String productName, int productImgId) {
        this.productName = productName;
        this.productImgId = productImgId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductImgId() {
        return productImgId;
    }

    public void setProductImgId(int productImgId) {
        this.productImgId = productImgId;
    }
}
