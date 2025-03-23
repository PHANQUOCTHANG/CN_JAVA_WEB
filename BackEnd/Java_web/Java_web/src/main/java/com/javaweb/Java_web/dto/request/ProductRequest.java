package com.javaweb.Java_web.dto.request;

public class ProductRequest {
    private String productName ;

    private String img ;
    private double price ;
    private String description ;

    private Boolean status ;

    private Long categoryId ;

    public ProductRequest() {
    }

    public ProductRequest(String productName, String img , double price, String description, Boolean status, Long categoryId) {
        this.productName = productName;
        this.img = img ;
        this.price = price;
        this.description = description;
        this.status = status;
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
