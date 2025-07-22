package com.review11;

public class Main {
    public static void main(String[] args) {
        Member m1 = new Member("봄이", "spring@dev.com");
        Book b1 = new Book("도서11", "저자1", 15000);
        Book b2 = new Book("도서22", "저자2", 20000);
        try {
            m1.buyBook(b1);
            m1.buyBook(b2);
            Order o1 = new Order(m1);

            o1.printOrder();
        } catch(InvalidPriceException e) {
            System.out.println(e.getMessage());
        }
    }
}

/*
🔎 피드백 요약
1. Book.totalBookCount는 Book 생성자에서 증가시키는 게 자연스러워!
지금은 buyBook()에서 증가시키고 있는데,
"책이 만들어질 때 책 수가 1 증가"하는 게 더 객체지향스러워.
2. InvalidPriceException은 super()로 메시지 넘기자!
현재 출력만 하고 있는데, Exception 내부 메시지 시스템도 활용하면 좋아
*/