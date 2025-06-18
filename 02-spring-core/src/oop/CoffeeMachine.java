package oop;

public class CoffeeMachine {
    String brand;

    public CoffeeMachine(String brand) {
        this.brand = brand;
    }

    public void brew(Barista barista) {
        System.out.println("바리스타 " + barista.getName() + "가 " + brand + " 커피머신으로 커피를 내립니다.");
    }
}