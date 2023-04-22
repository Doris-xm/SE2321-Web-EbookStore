package com.example.ebook_back.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
public class OrderCommit {

    private String receiver;
    private String phone;
    private String address;
    private String address_detail;
    private double totalPrice;
    private List<Integer> bookIDs;
    private List<Integer> bookNums;
}


