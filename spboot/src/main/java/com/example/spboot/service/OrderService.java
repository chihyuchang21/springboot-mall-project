package com.example.spboot.service;

import com.example.spboot.dto.CreateOrderRequest;


public interface OrderService {


    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
