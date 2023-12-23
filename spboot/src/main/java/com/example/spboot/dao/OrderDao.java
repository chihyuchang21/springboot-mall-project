package com.example.spboot.dao;

import com.example.spboot.dto.OrderQueryParams;
import com.example.spboot.model.Order;
import com.example.spboot.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);


    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

}
