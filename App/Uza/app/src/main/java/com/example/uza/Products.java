package com.example.uza;

public class Products {
    private String product_id;
    private String product_name;
    private String category;
    private String description;
    private String quantity;
    private String original_price;
    private String price;
    private String seller_name;
    private String rating;
    private String percentage_strike;
    private String product_image;
    private String date_added;

    public Products(String product_id, String product_name, String category, String description, String quantity, String original_price, String price, String seller_name, String rating, String percentage_strike, String product_image, String date_added) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.category = category;
        this.description = description;
        this.quantity = quantity;
        this.original_price = original_price;
        this.price = price;
        this.seller_name = seller_name;
        this.rating = rating;
        this.percentage_strike = percentage_strike;
        this.product_image = product_image;
        this.date_added = date_added;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPercentage_strike() {
        return percentage_strike;
    }

    public void setPercentage_strike(String percentage_strike) {
        this.percentage_strike = percentage_strike;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }
}
