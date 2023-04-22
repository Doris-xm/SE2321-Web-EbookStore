package com.example.ebook_back.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "myorder")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "orderID")
public class Order {
    @Id
    @Column(name = "orderID")
    private Long orderID;
    private Long userID;
    private int state;
    private String address;
    @Column(name = "totalprice")
    private double totalPrice;
    @Column(name = "createtime")
    private java.util.Date createTime;
    @Column(name = "finishtime")
    private java.util.Date finishTime;

    private String comment;
}


