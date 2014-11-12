package com.thoughtworks.server.service;

import com.thoughtworks.server.dao.ItemDao;
import com.thoughtworks.server.dao.ItemDaoImpl;
import com.thoughtworks.server.model.Category;
import com.thoughtworks.server.model.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ItemServiceImplTest {
    ItemDao itemDaoImpl;
    Item item;
    ItemService itemServiceImpl;
    List<Item> items = new ArrayList<Item>();

    @Before
    public void mock_itemDaoImpl(){
        itemDaoImpl = mock(ItemDaoImpl.class);

        int id = 1;
        Category category = new Category(1, "fruit");
        item = new Item(1, "葡萄", 6.5, "斤", category);
        items.add(item);

        when(itemDaoImpl.getItemById(id)).thenReturn(item);
        when(itemDaoImpl.getItems()).thenReturn(items);

        itemServiceImpl = new ItemServiceImpl();
        itemServiceImpl.setItemDaoImpl(itemDaoImpl);
    }

    @Test
    public void can_get_item_by_id(){
        assertThat(itemServiceImpl.getItemById(1)).isEqualTo(item);
        verify(itemDaoImpl).getItemById(1);
    }

    @Test
    public void can_delete_item_by_id(){
        itemServiceImpl.deleteItemById(1);
        verify(itemDaoImpl).deleteItemById(1);
    }

    @Test
    public void can_get_all_items(){
        assertThat(itemServiceImpl.getItems().size()).isEqualTo(1);
        verify(itemDaoImpl).getItems();
    }

    @Test
    public void can_insert_item(){
        itemServiceImpl.insertItem(item);
        verify(itemDaoImpl).insertItem(item);
    }

    @Test
    public void can_update_item_by_id(){
        itemServiceImpl.updateItemById(item);
        verify(itemDaoImpl).updateItemById(item);
    }
}
