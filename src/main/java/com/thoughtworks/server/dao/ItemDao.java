package com.thoughtworks.server.dao;

import com.thoughtworks.server.model.Item;

import java.util.List;

public interface ItemDao {
    Item getItemById(int id);

    List<Item> getItems();

    void deleteItemById(int id);

    void insertItem(Item item);

    void updateItemById(Item item);
}
