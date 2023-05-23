package com.example.ebook_back.controller;

import com.example.ebook_back.entity.Cart;
import com.example.ebook_back.service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") //允许跨域
public class CartController {
    @Resource
    private CartService cartService;

//    @RequestMapping("/cartbook")
//    public Cart getByBookID(@RequestParam("bookID")int bookID) {
//        return  cartService.findByBookId(bookID);
//    }
    @RequestMapping("/cart")
    public List<Cart> getCart(@RequestParam("userID")int userID) {
        return cartService.getCart(userID);
    }

    @PostMapping
    @RequestMapping("/addtocart")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> addToCart(@RequestBody String data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataArrayNode = mapper.readTree(data); // 读取数据(json string 数组)
        int[] dataArray = new int[dataArrayNode.size()]; // 创建数组
        for (int i = 0; i < dataArrayNode.size(); i++) {
            dataArray[i] = dataArrayNode.get(i).asInt(); // 转换为int
        }
        int userID = dataArray[0];
        int bookID = dataArray[1];


        // 返回成功的消息
        if( cartService.addBook(userID, bookID)) {
            return ResponseEntity.ok("Order created successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order creation failed");
        }
    }
    @PostMapping
    @RequestMapping("/cartquantity")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> changeQuantity(@RequestBody String data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataArrayNode = mapper.readTree(data); // 读取数据(json string 数组)
        int[] dataArray = new int[dataArrayNode.size()]; // 创建数组
        for (int i = 0; i < dataArrayNode.size(); i++) {
            dataArray[i] = dataArrayNode.get(i).asInt(); // 转换为int
        }
        int userID = dataArray[0];
        int bookID = dataArray[1];
        int quantity = dataArray[2];


        // 返回成功的消息
        if( cartService.changeQuantity(userID, bookID, quantity)) {
            return ResponseEntity.ok("Order created successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order creation failed");
        }
    }

    @PostMapping
    @RequestMapping("/cartclear")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> clear(@RequestBody String data) throws JsonProcessingException {
        int userID = Integer.parseInt(data);
        // 返回成功的消息
        if( cartService.clear(userID)) {
            return ResponseEntity.ok("Cart clear successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cart clear failed");
        }
    }
}
