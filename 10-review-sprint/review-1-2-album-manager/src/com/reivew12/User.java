package com.reivew12;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private List<Sellable> albums = new ArrayList<>();

    // Constructor
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // getter
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Sellable> getAlbums() { return albums; }

    public void buyAlbum(Sellable album) throws InvalidPriceException {
        if (album.getDiscountPrice() <= 0) {
            throw new InvalidPriceException();
        }

        albums.add(album);
        Album.totalPurchasePrice += album.getDiscountPrice();
    }
}