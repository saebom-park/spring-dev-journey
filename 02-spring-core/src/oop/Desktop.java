package oop;

public class Desktop extends Device {
    @Override
    public void powerOn() {
        System.out.println("Desktop is powering on with AC power.");
    }

    public static void main(String[] args) {
        Device[] devices = {new Laptop(), new Desktop()};
        for (Device d : devices) {
            d.powerOn();    // 각각 자식 클래스의 구현이 실행됨
        }
    }
}