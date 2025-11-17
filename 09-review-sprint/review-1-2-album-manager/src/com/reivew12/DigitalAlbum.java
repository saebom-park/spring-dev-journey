package com.reivew12;

public class DigitalAlbum extends Album {
    //Constructor
    public DigitalAlbum(String title, String artist, int price) {
        super(title, artist, price);
    }

    // getter
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public int getPrice() { return price; }

    @Override
    public int getDiscountPrice() {
        return (int) Math.round(price * 0.9);
    }
}