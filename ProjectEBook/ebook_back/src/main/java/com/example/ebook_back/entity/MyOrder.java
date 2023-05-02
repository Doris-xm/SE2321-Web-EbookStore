package com.example.ebook_back.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Column(name = "totalprice")
    private double totalprice;

    @Column(name = "createtime")
    private LocalDateTime createtime;

    @Column(name = "finishtime")
    private LocalDateTime finishtime;

    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookOrder> bookOrders = new ArrayList<>();


    // getters and setters
    public void addBookOrder(BookOrder bookOrder) {

    }
    public void createOrder(OrderCommit order) {
        orderID = order.getOrderID();
        userID = order.getUserID();
        state = order.getState();
        address = order.getAddress();
        totalprice = order.getTotalPrice();
        createtime = order.getCreatetime();
        finishtime = null;
        comment = null;

        IntStream.range(0, order.getBookIDs().size())
                .forEach(i -> {
                    BookOrder bookOrder = new BookOrder();
                    bookOrder.setOrder(this);
                    bookOrder.setBookID(order.getBookIDs().get(i));
                    bookOrder.setQuantity(order.getBookNums().get(i));
                    bookOrder.setTotalprice(order.getTotalPrice());
                    bookOrders.add(bookOrder);
                });

    }
}

