package com.thoughtworks.server.service;

import com.thoughtworks.server.dao.ItemDao;
import com.thoughtworks.server.model.Item;

import java.util.List;

public interface ItemService {
    Item getItemById(int id);

    void setItemDaoImpl(ItemDao itemDaoImpl);

    List<Item> getItems();

    void deleteItemById(int id);

    void insertItem(Item item);

    void updateItemById(Item item);
}
