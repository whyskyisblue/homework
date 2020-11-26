package com.example.main.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BookVO {

    //
    private Integer id;

    private String title;

    private String author;

    private String publisher;

    //　出版日
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date date;

    //値段
    private Long cost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

}