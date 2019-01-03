package com.alliance.foodintern.model;

public class FoodData {

    private String discount;
    private String foodName;
    private String description;
    private String price;
    private String image;
    private String menuId;


    public FoodData(String discount, String foodName, String description, String price, String image, String menuId) {
        this.discount = discount;
        this.foodName = foodName;
        this.description = description;
        this.price = price;
        this.image = image;
        this.menuId = menuId;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
