package com.example.ebook_back.entity;

import com.example.ebook_back.dao.BookDao;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;

@Data
@Entity
@Table(name = "cart")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "userID")
    private int userID;


    @Column(name = "bookID")
    private int bookID;

    @Column(name = "quantity")
    private int quantity;


    public Cart(int ui, int bi, int q) {
        userID = ui;
        bookID = bi;
        quantity = q;
    }

    public Cart() {

    }
}
