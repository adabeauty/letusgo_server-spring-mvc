package com.thoughtworks.server.controller;

import com.thoughtworks.server.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PaymentController {
    @Autowired
    PaymentService paymentServiceImpl;

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public void deleteCartItems(){
        paymentServiceImpl.deleteCartItems();
    }
}
