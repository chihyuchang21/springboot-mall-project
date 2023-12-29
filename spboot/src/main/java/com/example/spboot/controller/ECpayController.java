package com.example.spboot.controller;

import com.example.spboot.service.impl.ECpayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ECpayController {

    @Autowired
    ECpayServiceImpl eCpayServiceImpl;

    @PostMapping("/ecpayCheckout")
    public String ecpayCheckout() {
        String aioCheckOutALLForm = eCpayServiceImpl.eCpayCheckout();

        return aioCheckOutALLForm;
    }
}
