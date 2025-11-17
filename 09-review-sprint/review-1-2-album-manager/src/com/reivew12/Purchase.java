package com.reivew12;

public class Purchase {
    private User user;

    // Constructor
    public Purchase(User user) { this.user = user;}

    // getter
    public User getUser() { return user; }

    public void printPurchaseInfo() {
        System.out.println("[앨범 구매 내역]");
        System.out.println("- 구매자 정보: " + user.getName() + "(" + user.getEmail() + ")");
        System.out.println("- 구매 목록: ");

        for (Sellable a : user.getAlbums()) {
            System.out.println(a);
        }

        System.out.println("- 총 결제 금액: " + Album.totalPurchasePrice + "원");
    }
}