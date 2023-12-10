package com.example.ebook_back.kafka;

import com.example.ebook_back.constant.MsgUtil;
import com.example.ebook_back.deserializer.OrderDeserializer;
import com.example.ebook_back.entity.Book;
import com.example.ebook_back.entity.BookOrder;
import com.example.ebook_back.entity.MyOrder;
import com.example.ebook_back.service.BookService;
import com.example.ebook_back.service.OrderService;
import net.sf.json.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
//@Component
@Controller
public class KafkaCon {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @Autowired
    private SynchronizedMsg synchronizedMsg;


    @KafkaListener(id = "orderListener", topics = "orders")
    @MessageMapping("/order_response")
    public void listenForOrders() throws Exception {
        // 处理下订单消息的逻辑
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "100");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, OrderDeserializer.class.getName());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer<String, MyOrder> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("orders"));

        while (true) {
            ConsumerRecords<String, MyOrder> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, MyOrder> record : records) {
                System.out.println("New Order:");
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                MyOrder newOrder = record.value();
                if( orderService.saveOrder(newOrder)) {
                    List<BookOrder> bookOrders = newOrder.getBookOrders();

                    for(BookOrder bookOrder: bookOrders) {
                        int bookId = bookOrder.getBookID();
                        int num = bookOrder.getQuantity();
                        Book book = bookService.findBookById(bookId);
                        book.setStocks(book.getStocks() - num);
                        book.setSales(book.getSales() + num);
                        bookService.addBook(book);
                    }
                        System.out.println(MsgUtil.ORDER_SUCCESS_MSG);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("id", newOrder.getOrderID());
                        jsonObject.put("price", newOrder.getTotalprice());
                        synchronizedMsg.sendMsgToUser(String.valueOf(newOrder.getUserID()), "/topic/order_response", jsonObject.toString());

                }
               else {
                    System.out.println(MsgUtil.ORDER_ERR_MSG);
                }
            }
        }
    }

//    @SendTo("/topic")
//    public String sendMessage(String message) throws Exception {
////        Thread.sleep(1000);
//        System.out.println("send message"+message);
//        return message;
//    }

}
