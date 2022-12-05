package com.jnu.student;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private String publisher;
    private int id;
    public Book(String title, String author, String publisher, int id) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}