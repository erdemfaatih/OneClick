package com.example.madfinalproject;

public class Menu {
    private String name;
    private String content;
    private String price;
    public Menu() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Menu(String name, String content, String price) {
        this.name = name;
        this.content = content;
        this.price = price;
    }
}
