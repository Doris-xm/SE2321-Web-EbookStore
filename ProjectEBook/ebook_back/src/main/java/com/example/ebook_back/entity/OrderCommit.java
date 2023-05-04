package com.example.ebook_back.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderCommit {
    //需要填写的部分
    private String receiver;
    private String phone;
    private String address;
    private String address_detail;
    private List<Double> totalPrice;
    private List<Integer> bookIDs;
    private List<Integer> bookNums;

    //不需要填写的部分
    private int orderID;
    private int state;
    private int userID;
    private LocalDateTime createtime;
}


