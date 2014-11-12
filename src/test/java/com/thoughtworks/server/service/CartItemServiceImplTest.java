package com.thoughtworks.server.service;


import com.thoughtworks.server.dao.CartItemDao;
import com.thoughtworks.server.dao.CartItemDaoImpl;
import com.thoughtworks.server.model.CartItem;
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

public class CartItemServiceImplTest {
    CartItemDao cartItemDaoImpl;
    CartItemService cartItemServiceImpl;
    CartItem cartItem;
    List<CartItem> cartItems = new ArrayList<CartItem>();

    @Before
    public void mock_cartItemDaoImpl(){
        cartItemDaoImpl = mock(CartItemDaoImpl.class);

        int id = 1;
        Category category = new Category(1, "fruit");
        Item item = new Item(1, "apple", 3.5, "kg", category);
        cartItem = new CartItem(1, item, 4);
        cartItems.add(cartItem);

        when(cartItemDaoImpl.getCartItemById(id)).thenReturn(cartItem);
        when(cartItemDaoImpl.getCartItems()).thenReturn(cartItems);

        cartItemServiceImpl = new CartItemServiceImpl();
        cartItemServiceImpl.setCartItemDaoImpl(cartItemDaoImpl);
    }

    @Test
    public void can_get_cartItem_by_id(){
        assertThat(cartItemServiceImpl.getCartItemById(1)).isEqualTo(cartItem);
        verify(cartItemDaoImpl).getCartItemById(1);
    }

    @Test
    public void can_get_all_cartItems(){
        assertThat(cartItemServiceImpl.getCartItems().size()).isEqualTo(1);
        verify(cartItemDaoImpl).getCartItems();
    }

    @Test
    public void can_insert_cartItem(){
        cartItemServiceImpl.insertCartItem(cartItem);
        verify(cartItemDaoImpl).insertCartItem(cartItem);
    }

    @Test
    public void can_delete_cartItem_by_id(){
        cartItemServiceImpl.deleteCartItemById(1);
        verify(cartItemDaoImpl).deleteCartItemById(1);
    }

    @Test
    public void can_update_cartItem_by_id(){
        cartItemServiceImpl.updateCartItemById(cartItem);
        verify(cartItemDaoImpl).updateCartItemById(cartItem);
    }
}
