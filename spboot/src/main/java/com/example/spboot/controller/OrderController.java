package com.example.spboot.controller;

import com.example.spboot.dto.CreateOrderRequest;
import com.example.spboot.dto.OrderQueryParams;
import com.example.spboot.model.Order;
import com.example.spboot.service.OrderService;
import com.example.spboot.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    //查詢商品列表
    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(
            //查詢條件 Filtering
            @PathVariable Integer userId,

            //分頁 Pagination //都是從url中取得的請求參數
            @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit, //取得幾筆商品數據
            @RequestParam(defaultValue = "0") @Min(0) Integer offset //跳過幾筆數據
    ) {
        //把前端傳過來的值set到這個變數中
        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offset);

        //取得 order list
        List<Order> orderList = orderService.getOrders(orderQueryParams);

        //取得 order 總數
        Integer count = orderService.countOrder(orderQueryParams);

        //分頁
        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);

        //返回productList給前端
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }


    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest) {


        Integer orderId = orderService.createOrder(userId, createOrderRequest);

        Order order = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);

    }
}
