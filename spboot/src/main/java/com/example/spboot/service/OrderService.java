package com.example.spboot.service;

import com.example.spboot.dto.CreateOrderRequest;
import com.example.spboot.model.Order;


public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
