package com.ecom.buylo.Model;

public class SCategory {
    private String categoryId;
    private String categoryName;

    public SCategory(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
}

