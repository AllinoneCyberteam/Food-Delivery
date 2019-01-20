package com.alliance.foodintern.model;

public class CardItem {
    String food_name,food_price,image,id;

    public CardItem(String food_name, String food_price, String image) {
        this.food_name = food_name;
        this.food_price = food_price;
        this.image = image;
    }

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

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
