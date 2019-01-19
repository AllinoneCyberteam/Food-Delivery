package com.alliance.foodintern.model;

public class CardItem {
    String food_name;

    public String getFood_name() {
        return food_name;
    }

    public CardItem(String food_name, String food_price, int id) {
        this.food_name = food_name;
        this.food_price = food_price;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String food_price;
    int id;

    public CardItem() {
    }
}
