package com.example.ebook_back.entity;
import com.example.ebook_back.entity.MyOrder;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.transaction.Transactional;

@Data
@Entity
@Table(name = "bookorder")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id", scope = BookOrder.class)
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**包含了一个 Hibernate 代理（proxy）对象
     * 并且此时 Session 已经关闭，导致无法初始化该代理对象。
     * Solve:使用 @JsonIgnore 注解标记关联实体的属性，从而告诉 Jackson 在序列化时忽略该属性
    */
//     @JsonIgnore
//     @ManyToOne(fetch = FetchType.EAGER)
//     @JoinColumn(name = "orderID")
//     private MyOrder order;
    @Column(name = "orderID")
    private int orderID;

    @Column(nullable = false,name = "total_price")
    private double totalprice;

    @Column(nullable = false,name = "bookID")
    private int bookID;
//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "bookID")
//    private Book book;


    @Column(nullable = false)
    private int quantity;


}
