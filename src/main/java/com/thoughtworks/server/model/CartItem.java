package com.thoughtworks.server.model;

public class CartItem {
    private int id;
    private Item item;
    private int num;

    public CartItem() {
    }

    public CartItem(int id, Item item, int num) {
        this.id = id;
        this.item = item;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}