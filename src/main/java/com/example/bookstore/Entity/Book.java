package com.example.bookstore.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "book")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private String price;

    @Column(name = "total_count")
    private int totalCount;

    @Column(name = "sold")
    private int sold;

    public Book(Long id, String author, String title, String category, String price, int totalCount, int sold) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.category = category;
        this.price = price;
        this.totalCount = totalCount;
        this.sold = sold;
    }

    public Book() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

}
