package com.example.cardatabase4.trash;

import lombok.Data;

@Data
public class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void displayInfo() {
        System.out.printf("Title : %s, Author: %s", title, author);
    }
}
