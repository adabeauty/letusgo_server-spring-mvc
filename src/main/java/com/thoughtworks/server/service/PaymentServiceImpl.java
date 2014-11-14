package com.thoughtworks.server.service;

import com.thoughtworks.server.dao.CartItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    CartItemDao cartItemDaoImpl;

    @Override
    public void deleteCartItems() {
        cartItemDaoImpl.deleteCartItems();
    }
}
