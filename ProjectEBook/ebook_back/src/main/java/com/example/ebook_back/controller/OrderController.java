package com.example.ebook_back.controller;
import com.example.ebook_back.constant.Constant;
import com.example.ebook_back.constant.Msg;
import com.example.ebook_back.constant.MsgCode;
import com.example.ebook_back.constant.MsgUtil;
import com.example.ebook_back.deserializer.OrderSerializer;
import com.example.ebook_back.entity.Book;
import com.example.ebook_back.entity.BookOrder;
import com.example.ebook_back.entity.MyOrder;
import com.example.ebook_back.entity.OrderCommit;
import com.example.ebook_back.service.BookService;
import com.example.ebook_back.service.OrderService;
import com.example.ebook_back.serviceImpl.TokenServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@RestController
@CrossOrigin(origins = "http://localhost:3000") //允许跨域
public class OrderController {
    @Resource
    private OrderService orderService;
    @Autowired
    private BookService bookService;
    @Autowired
    private TokenServiceImpl tokenService;
    @GetMapping("/orders")
//    public List<MyOrder> getOrdersById(@RequestParam("id") int id) {
//        return orderService.findOrderById(id);
//    }
    public List<MyOrder> getOrdersById(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        int id = tokenService.getUserIdFromToken(token);
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

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.setProperty("transactional.id", "my-transactional-id");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, OrderSerializer.class.getName());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        Producer<String, MyOrder> producer = null;
        // 发送订单到kafka
        try {
            producer = new KafkaProducer<>(props);
            producer.initTransactions();
            producer.beginTransaction();
            // 发送订单
            producer.send(new ProducerRecord<String, MyOrder>("orders", Integer.toString(newOrder.getOrderID()), newOrder));
            producer.commitTransaction();
        } catch (ProducerFencedException e) {
            if (producer != null)
                producer.close();
            System.out.println(e);
        } catch (OutOfOrderSequenceException e) {
            if (producer != null)
                producer.close();
            System.out.println(e);
        } catch (AuthorizationException e) {
            if (producer != null)
                producer.close();
            System.out.println(e);
        } catch (KafkaException e) {
            if(producer != null)
                producer.abortTransaction();
            System.out.println(e);
        }

        // 返回成功的消息
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.ORDER_SUCCESS_MSG);
//        if( orderService.saveOrder(newOrder)) {
//            List<BookOrder> bookOrders = newOrder.getBookOrders();
//
//            for(BookOrder bookOrder: bookOrders) {
//                int bookId = bookOrder.getBookID();
//                int num = bookOrder.getQuantity();
//                Book book = bookService.findBookById(bookId);
//                book.setStocks(book.getStocks() - num);
//                book.setSales(book.getSales() + num);
//                bookService.addBook(book);
//            }
//            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.ORDER_SUCCESS_MSG);
//        }
//       else {
//            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.ORDER_ERR_MSG);
//        }
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
    @PostMapping
    @RequestMapping("/all_order_items")
    public Msg AllOrderItems() {
        List<BookOrder> bookOrders = orderService.findAllOrderItems();
        JSONArray jsonArray = new JSONArray();
        for (BookOrder bookOrder : bookOrders) {
            jsonArray.add(JSONObject.fromObject(bookOrder));
        }
        JSONObject result = new JSONObject();
        result.put("bookOrders", jsonArray);

        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG,result);
    }
}
