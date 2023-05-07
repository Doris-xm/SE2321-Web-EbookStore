package com.example.ebook_back.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

@Data
@Entity
@Table(name = "myorder")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "orderID")
public class MyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
    private int orderID;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userID")
//    private User user;
    @Column(name = "userID")
    private int userID;

    @Column(name = "state")
    private int state;

    @Column(name = "address")
    private String address;

    @Column(name = "total_price")
    private double totalprice;

    @Column(name = "createtime")
    private LocalDateTime createtime;

    @Column(name = "finishtime")
    private LocalDateTime finishtime;

    @Column(name = "comment")
    private String comment;

    @Column(name = "receiver")
    private String receiver;
    @Column(name = "phone")
    private String phone;

    @Transient
    @OneToMany(mappedBy = "orderID", cascade = CascadeType.ALL, orphanRemoval = true) // 一对多关系,使用级联操作因为BookOrder中的orderID是外键，删除MyOrder时也会删除BookOrder
    //检查session是否关闭，如果关闭则重新打开
    private List<BookOrder> bookOrders = new ArrayList<>();


    public MyOrder() {}

    // getters and setters
    public void createOrder(OrderCommit order) {
//        orderID = null;
        userID = order.getUserID();
        state = order.getState();
        address = order.getAddress();
        createtime = order.getCreatetime();
        finishtime = null;
        comment = null;
        receiver = order.getReceiver();
        phone = order.getPhone();

        IntStream.range(0, order.getBookIDs().size())
                .forEach(i -> {
                    BookOrder bookOrder = new BookOrder();
//                    bookOrder.setOrder(this);
                    bookOrder.setOrderID(this.getOrderID());
                    bookOrder.setBookID(order.getBookIDs().get(i));
                    bookOrder.setQuantity(order.getBookNums().get(i));
                    System.out.println("bookOrder_price " + totalprice);
                    bookOrder.setTotalprice(order.getTotalPrice().get(i));
                    totalprice += order.getTotalPrice().get(i);
                    bookOrders.add(bookOrder);
                });

    }
}

