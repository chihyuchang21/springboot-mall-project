package com.example.spboot.dao;

import com.example.spboot.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, Integer totalAmount);


    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

}
