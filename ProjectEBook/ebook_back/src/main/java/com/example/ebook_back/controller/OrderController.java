package com.example.ebook_back.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.ebook_back.entity.MyOrder;
import com.example.ebook_back.entity.OrderCommit;
import com.example.ebook_back.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") //允许跨域
public class OrderController {
    @Resource
    private OrderService orderService;
    @GetMapping("/orders")
    public List<MyOrder> getAllOrders(@RequestParam("id") int id) {
        return orderService.findOrderById(id);

    }

    @PostMapping
    @RequestMapping("/sendorders")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> createOrder(@RequestBody OrderCommit order) {
        // TODO:保存订单到数据库
        MyOrder newOrder = new MyOrder();
        newOrder.createOrder(order);

        System.out.println("New Order:");
        System.out.println(order.toString());
        // 返回成功的消息
        if( orderService.saveOrder(newOrder)) {
            return ResponseEntity.ok("Order created successfully");
        }
       else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order creation failed");
        }
    }

}
