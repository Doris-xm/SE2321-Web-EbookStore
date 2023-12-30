package org.wordCount.entity;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "book_detail")
public class Book {
    @Id
    private Integer id;
    private String author;
    private String cover;
    private String isbn;
    private String introduce;
    private String title;
    private Double price;
    private List<String> type;
    public Book() {
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
    public List<String> getType() {
        return type;
    }
    public void setType(List<String> type) {
        this.type = type;
    }
}
