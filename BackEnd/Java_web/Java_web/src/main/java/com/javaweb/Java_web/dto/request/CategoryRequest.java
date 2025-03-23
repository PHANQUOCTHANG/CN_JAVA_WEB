package com.javaweb.Java_web.dto.request;

import jakarta.validation.constraints.Size;

public class CategoryRequest {

    @Size(min = 3, max = 50, message = "Category name must be between 3 and 50 characters") // Độ dài tối thiểu 3, tối đa 50 ký tự
    private String categoryName;

    public CategoryRequest() {
    }

    public CategoryRequest(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
