package com.example.uza;

public class Orders {
    private String order_number;
    private String items;
    private String d_date;

    public Orders(String order_number, String items, String d_date) {
        this.order_number = order_number;
        this.items = items;
        this.d_date = d_date;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getD_date() {
        return d_date;
    }

    public void setD_date(String d_date) {
        this.d_date = d_date;
    }
}
