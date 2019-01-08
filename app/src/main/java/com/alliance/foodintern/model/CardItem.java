package com.alliance.foodintern.model;

public class CardItem {
    String food_name,food_price;

    public CardItem(String food_name, String food_price) {
        this.food_name = food_name;
        this.food_price = food_price;
    }

    public CardItem() {
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_price() {
        return food_price;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }
}