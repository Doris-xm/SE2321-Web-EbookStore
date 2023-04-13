package com.example.ebook_back.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.ebook_back.entity.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class OrderController {
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1L, "The Great Gatsby",9.99F,1,"Shanghai Jiao Tong University", Order.State.PAID));
        orders.add(new Order(2L, "To Kill a Mockingbird",8.99F,3,"Xian Jiao Tong University", Order.State.SHIPPED));
        orders.add(new Order(3L, "The Catcher in the Rye",7.99F,2,"Zhe Jiang University", Order.State.REACHED));
        orders.add(new Order(4L, "The Grapes of Wrath",16.99F,1,"Beijing Jiao Tong University", Order.State.PAID));
        orders.add(new Order(5L, "The Lord of the Rings",25.99F,4,"Fudan Jiao Tong University", Order.State.REACHED));
       return orders;
    }

    @RequestMapping("/orders")
    public String home() {
        String ordersString = JSON.toJSONString(getAllOrders(), SerializerFeature.BrowserCompatible);
        return ordersString;
    }
}
