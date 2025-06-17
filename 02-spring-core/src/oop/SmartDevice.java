package oop;

public class SmartDevice implements Charger, Browser {
    @Override
    public void charge() {
        System.out.println("충전중...");
    }

    @Override
    public void browse() {
        System.out.println("웹 브라우징 중...");
    }

    public static void main(String[] args) {
        SmartDevice device = new SmartDevice();
        device.charge();
        device.browse();
    }
}