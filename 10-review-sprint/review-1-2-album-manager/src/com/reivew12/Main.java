package com.reivew12;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user = new User("봄이", "spring@dev.com");

        List<Sellable> albums = new ArrayList<>();

        albums.add(new DigitalAlbum("앨범1", "가수1", 15900));
        albums.add(new PhysicalAlbum("앨범2", "가수2", 25850));

        try {
            for (Sellable album : albums) {
                user.buyAlbum(album);
            }
            Purchase purchase = new Purchase(user);
            purchase.printPurchaseInfo();
        } catch (InvalidPriceException e) {
            System.out.println(e.getMessage());
        }
    }
}

/* 🔍 피드백 요약
1. Album.discount는 static 필드로 둘 필요 없어!
   각 앨범 타입(Digital/Physical)이 고유한 할인 정책을 가지므로,
   discount는 각 클래스에서 직접 상수로 구현하는 게 더 객체지향적이야.

2. Album 생성자에서 totalPurchasePrice를 누적하지 말자!
   생성자에서는 아직 오버라이딩된 getDiscountPrice()가 정확히 작동하지 않을 수 있어서,
   구매 시점(User.buyAlbum)에서 누적하는 게 더 안전하고 명확해.

3. PhysicalAlbum이 굳이 Sellable을 다시 implements 할 필요는 없어!
   이미 부모 클래스인 Album이 Sellable을 구현하고 있어서 중복이야.

4. getDiscountPrice()에서 int 강제 변환 시 소수점 절삭에 주의!
   경우에 따라 정수 절삭이 의미를 왜곡할 수 있으니, 출력 시 소수 첫째자리까지 반올림(Math.round) 처리하자.

5. System.out.println(user.getAlbums())는 보기 불편해!
   for-each 문을 사용해서 앨범 하나씩 직접 출력하면 가독성이 훨씬 좋아져.
   (Album.toString()이 잘 구현돼 있으므로 그대로 출력하면 깔끔하게 나온다)

6. 가격이 0원 이하인 앨범은 예외로 처리하자!
   User.buyAlbum()에서 할인 가격이 0 이하일 경우 InvalidPriceException을 발생시키면,
   잘못된 데이터 입력을 사전에 차단할 수 있어.
*/