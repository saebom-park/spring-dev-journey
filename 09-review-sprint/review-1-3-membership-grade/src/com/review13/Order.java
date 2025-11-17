package com.review13;

public class Order {
    private String itemName;
    private int price;
    // constructor
    public Order(String itemName, int price) {
        this.itemName = itemName;
        this.price = price;
    }

    // getter
    public String getItemName() { return itemName; }
    public int getPrice() { return price; }
    public int getDiscountPrice(Member member) {
        // 피드백 2 수정
        double discountRate = 1 - member.getDiscountRate();
        return (int) Math.round(price * discountRate);
    }
    public int getPoint(Member member) { return (int) Math.round(price * member.getPointRate()); }

    public void printOrderInfo(Member member) {
        System.out.println(
                "- 상품명: " + itemName + " / 정가: " + price + "원 / 할인가: " + getDiscountPrice(member) + "원 / 적립 금액: " + getPoint(member) + "원"
        );
    }
}