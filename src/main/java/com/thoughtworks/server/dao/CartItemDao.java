package com.thoughtworks.server.dao;


import com.thoughtworks.server.model.CartItem;

import java.util.List;

public interface CartItemDao {
    CartItem getCartItemById(int id);

    List<CartItem> getCartItems();

    void insertCartItem(CartItem cartItem);

    void deleteCartItemById(int id);

    void updateCartItemById(CartItem cartItem);

    void deleteCartItems();
}
