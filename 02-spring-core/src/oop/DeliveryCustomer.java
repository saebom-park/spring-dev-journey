package oop;
import java.time.LocalDate;

public class DeliveryCustomer {
    private String name;
    private String goods;

    public DeliveryCustomer(String name, String goods) {
        this.name = name;
        this.goods = goods;
    }

    public String getName() {
        return name;
    }

    public String getGoods() {
        return goods;
    }

    public void requestDelivery(Courier courier) {
        LocalDate sendDate = LocalDate.now();

        System.out.println(name + " 고객이 택배를 요청하였습니다.");
        System.out.println("- 배송 상품: " + goods);
        System.out.println("- 발송일: " + sendDate);

        courier.deliver(this);
    }
}