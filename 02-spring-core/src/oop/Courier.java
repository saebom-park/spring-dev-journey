package oop;

public class Courier {
    private String name;
    private DeliveryVehicle dv;


    public Courier(String name, DeliveryVehicle dv) {
        this.name = name;
        this.dv = dv;
    }

    public void deliver(DeliveryCustomer dc) {
        System.out.println("배달원이 지정 되었습니다.");
        System.out.println(name + " 배달원이 " + dc.getName() + " 고객의 상품 '" + dc.getGoods() + "'에 대해" + " 배송 준비를 시작합니다.");
        dv.move();

        System.out.println("배송이 완료되었습니다.");
    }
}