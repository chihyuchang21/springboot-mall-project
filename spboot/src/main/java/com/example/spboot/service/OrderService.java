package com.example.spboot.service;

import com.example.spboot.dto.CreateOrderRequest;
import com.example.spboot.dto.OrderQueryParams;
import com.example.spboot.model.Order;

import java.util.List;


public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
