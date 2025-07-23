package com.reivew12;

public class Album implements Sellable {
    public String title;
    public String artist;
    public int price;
    public static int totalPurchasePrice = 0;

    // Constructor
    public Album() {}
    public Album(String title, String artist, int price) {
        this.title = title;
        this.artist = artist;
        this.price = price;
    }

    @Override
    public String toString() {
        return " * 앨범명: " + title + " / 아티스트: " + artist + " / 정가: " + price + "원 / 할인가: " + getDiscountPrice() + "원";
    }

    @Override
    public int getDiscountPrice() { return price; }
}