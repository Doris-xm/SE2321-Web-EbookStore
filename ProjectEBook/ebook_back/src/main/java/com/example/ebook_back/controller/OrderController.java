package com.example.ebook_back.controller;
import com.example.ebook_back.constant.Constant;
import com.example.ebook_back.constant.Msg;
import com.example.ebook_back.constant.MsgCode;
import com.example.ebook_back.constant.MsgUtil;
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
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000") //允许跨域
public class OrderController {
    @Resource
    private OrderService orderService;
    @GetMapping("/orders")
    public List<MyOrder> getOrdersById(@RequestParam("id") int id) {
        return orderService.findOrderById(id);
    }
    @RequestMapping("/allorders")
    public List<MyOrder> getAllOrders() {
        return orderService.findAll();
    }
    @PostMapping
    @RequestMapping("/sendorders")
    public Msg createOrder(@RequestBody OrderCommit order) {
        // 保存订单到数据库
        MyOrder newOrder = new MyOrder();
        newOrder.createOrder(order);

        System.out.println("New Order:");
        System.out.println(order.toString());
        // 返回成功的消息
        if( orderService.saveOrder(newOrder)) {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.ORDER_SUCCESS_MSG);
        }
       else {
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.ORDER_ERR_MSG);
        }
    }
    @PostMapping
    @RequestMapping("/change_state")
    public Msg changeState(@RequestBody Map<String,Object> data) {
        Integer orderId = Integer.valueOf(data.get(Constant.ORDER_ID).toString());
        Integer state = Integer.valueOf(data.get(Constant.ORDER_STATE).toString());
        if( orderService.changeState(orderId,state)) {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.UPDATE_SUCCESS_MSG);
//            return ResponseEntity.ok("订单状态修改成功");
        } else {
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.UPDATE_ERR_MSG);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("状态修改失败！");
        }
    }
}
