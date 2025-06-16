// * 실습 미션: 상속 구조 구성해보기
// 1. `Appliance` 클래스: `brand` 필드, `powerOn()` → `"Brand X appliance is on."`
// 2. `Washer` 클래스: `capacity` 필드 추가, `powerOn()` 오버라이딩
// 3. `SmartWasher` 클래스: `wifiEnabled` 필드 추가, `powerOn()` 오버라이딩
// 4. 각 생성자 `super()`로 연결, `powerOn()`은 `super.powerOn()` 포함하여 출력 구성
// 5. 실행 위치에서 `SmartWasher` 생성 후 `powerOn()` 호출

package oop;

public class Appliance {
    protected String brand;

    public Appliance(String brand) {
        this.brand = brand;
        System.out.println("Appliance Created!");
    }

    public void powerOn() {
        System.out.println("Brand " + brand + " appliance is on.");
    }
}