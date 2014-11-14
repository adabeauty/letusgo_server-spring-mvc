package com.thoughtworks.server.service;

import com.thoughtworks.server.dao.CartItemDao;
import com.thoughtworks.server.dao.CartItemDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class PaymentServiceTest {
    PaymentService paymentServiceImpl;
    CartItemDao cartItemDaoImpl;

    @Before
    public void mock_paymentDao(){
        cartItemDaoImpl = mock(CartItemDaoImpl.class);

        paymentServiceImpl= new PaymentServiceImpl();
        ReflectionTestUtils.setField(paymentServiceImpl, "cartItemDaoImpl", cartItemDaoImpl);
    }

    @Test
    public void can_delete_all_cartItems(){
        paymentServiceImpl.deleteCartItems();
        verify(cartItemDaoImpl).deleteCartItems();
    }
}
