package oop;

public class Car {
    String model;
    int speed;
    
    public void drive() {
        System.out.println(model + "가 " + speed + "km/h 속도로 부릉부릉~ 달립니다!");
    }
    
    public static void main(String[] args) {
        Car myCar = new Car();  // 객체 생성
        myCar.model = "소나타"; // 속성 설정
        myCar.speed = 100;
        myCar.drive();  // 메서드 호출
    }
}