package com.example.ebook_back.entity;

public class Book {
    private Long id;
    private String title;
    private String author;
    private float price;
    private String cover;

    public Book(Long id, String title, String author, float price, String cover) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.cover = cover;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {   this.price = price;  }

    public String getCover() {  return cover; }
    public void setCover(String cover) {    this.cover = cover;}


    @Override
    public String toString() {
        return String.format(
                "Book[id=%d, title='%s', author='%s', price='%f', cover='%s']",
                id, title, author, price, cover);
    }

}
