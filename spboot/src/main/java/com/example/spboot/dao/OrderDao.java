package com.example.spboot.dao;

import com.example.spboot.model.Order;
import com.example.spboot.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);


    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

}
