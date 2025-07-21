package com.review1;

public class Main {
    public static void main(String[] args) {
        Member m1 = new Member("봄이", "spring@dev.com");
        Book b1 = new Book("제목1", "저자1", 15000);
        Book b2 = new Book("제목2", "저자2", 25000);

        m1.buyBook(b1);
        m1.buyBook(b2);

        Order o1 = new Order(m1);
        System.out.println(o1.getOrder());
        System.out.println(Book.totalBookCount);
    }
}