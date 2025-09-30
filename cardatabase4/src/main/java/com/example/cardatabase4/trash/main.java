package com.example.cardatabase4.trash;

public class main {
    public static void main(String[] args) {
        Book book = new Book("Java", "Nam");
        EBook eBook = new EBook("Spring Boot 3", "Kim", 20.5);

        book.displayInfo();
        eBook.displayInfo();
    }
}
