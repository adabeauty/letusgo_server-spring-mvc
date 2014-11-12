package com.thoughtworks.server.service;

import com.thoughtworks.server.dao.ItemDao;
import com.thoughtworks.server.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDaoImpl;

    @Override
    public void setItemDaoImpl(ItemDao itemDaoImpl) {
        this.itemDaoImpl = itemDaoImpl;
    }

    @Override
    public Item getItemById(int id) {
        return itemDaoImpl.getItemById(id);
    }

    @Override
    public void deleteItemById(int id) {
        itemDaoImpl.deleteItemById(id);
    }

    @Override
    public void insertItem(Item item) {
        itemDaoImpl.insertItem(item);
    }

    @Override
    public void updateItemById(Item item) {
        itemDaoImpl.updateItemById(item);
    }

    @Override
    public List<Item> getItems() {
        return itemDaoImpl.getItems();
    }


}
