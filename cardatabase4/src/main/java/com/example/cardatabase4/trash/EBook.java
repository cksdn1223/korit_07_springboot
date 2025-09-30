package com.example.cardatabase4.trash;

public class EBook extends Book{
    private double fileSize;

    public EBook(String title, String author, double fileSize) {
        super(title, author);
        this.fileSize = fileSize;
    }

    @Override
    public void displayInfo() {
        System.out.println();
        super.displayInfo();
        System.out.printf(", File Size: %f", fileSize);
    }
}
