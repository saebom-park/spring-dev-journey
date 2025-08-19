package com.springlab14.practice2;

public class Customer {
    private int id;
    private String name;
    private String email;

    // getter
    public int getId() {return id;}
    public String getName() {return name;}
    public String getEmail() {return email;}

    // setter
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}

    @Override
    public String toString() {
        return "Customer{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}