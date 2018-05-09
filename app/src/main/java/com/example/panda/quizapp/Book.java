package com.example.panda.quizapp;

/**
 * Created by pandatrooper on 6/11/17.
 */

public class Book {
    private int id;
    private String name;
    private String price;
    private byte[] image;

    public Book(String name, String price, byte[] image, int id) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
