package com.thoughtworks.server.controller;
import com.thoughtworks.server.model.Item;
import com.thoughtworks.server.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ItemController {
    @Autowired
    private ItemService itemServiceImpl;

    @RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
    public @ResponseBody Item getItemById(@PathVariable int id){
        return itemServiceImpl. getItemById(id);
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public void insertItem(@RequestBody Item item){
        itemServiceImpl.insertItem(item);
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
    public void deleteItemById(@PathVariable int id){
        itemServiceImpl. deleteItemById(id);
    }

    @RequestMapping(value = "items/{id}", method = RequestMethod.PUT)
    public void updateItemById(@RequestBody Item item){
        itemServiceImpl.updateItemById(item);
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public @ResponseBody List<Item> getItems(){
        return itemServiceImpl. getItems();
    }
}
