package com.example.ebook_back.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;

@Document(collection = "book_detail")
public class BookDetail {
    @Id
    private Integer id;
    private String author;
    private String cover;
    private String isbn;
    private String introduce;
    private String title;
    private Double price;
    public BookDetail(int id, String author, String cover, String isbn, String introduce, String title, Double price) {
        this.id = id;
        this.author = author;
        this.cover = cover;
        this.isbn = isbn;
        this.introduce = introduce;
        this.title = title;
        this.price = price;
    }
    public BookDetail() {
    }
    public int getId() {
        return id;
    }
    public String getAuthor() {
        return author;
    }
    public String getCover() {
        return cover;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getIntroduce() {
        return introduce;
    }
    public String getTitle() {
        return title;
    }
    public Double getPrice() {
        return price;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
