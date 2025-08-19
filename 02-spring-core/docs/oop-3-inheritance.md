# [OOP-3단계] 상속 구조 이해

> “모든 Vehicle은 달릴 수 있다.
> 
> 
> 하지만 Bicycle은 페달이 있고, E-Bike는 배터리도 있지!”
> 
> 👉 바로 **이런 계층 구조를 상속과 오버라이딩으로 표현하는 게 객체지향 설계!**
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| `extends` | 상속을 위한 키워드 (자식 → 부모) |
| `super()` | 부모 클래스의 생성자 호출 |
| `@Override` | 부모 메서드를 자식이 재정의할 때 사용 |
| 생성자 체인 | 하위 클래스 → 중간 클래스 → 상위 클래스 순으로 연결 |
| 설계 원칙 | 공통 기능은 부모에, 세부 기능은 자식에 구현 |

---

### 🧾 예시 코드

```java
// Base class
class Vehicle {
    protected String name;

    public Vehicle(String name) {
        this.name = name;
        System.out.println("🚗 Vehicle created");
    }

    public void move() {
        System.out.println(name + " is moving.");
    }
}

// Middle class
class Bicycle extends Vehicle {
    protected String pedalType;

    public Bicycle(String name, String pedalType) {
        super(name);
        this.pedalType = pedalType;
        System.out.println("🚲 Bicycle created");
    }

    @Override
    public void move() {
        super.move();
        System.out.println("Pedal type: " + pedalType);
    }
}

// Subclass
class ElectricBike extends Bicycle {
    private int batteryLevel;

    public ElectricBike(String name, String pedalType, int batteryLevel) {
        super(name, pedalType);
        this.batteryLevel = batteryLevel;
        System.out.println("⚡ ElectricBike created");
    }

    @Override
    public void move() {
        super.move();
        System.out.println("Battery level: " + batteryLevel + "%");
    }
}

// Test class
public class VehicleExample {
    public static void main(String[] args) {
        ElectricBike ebike = new ElectricBike("봄이의 E-Bike", "Clipless", 85);
        ebike.move();
    }
}
```

---

### 📌 포인트 요약

- ✅ `extends`로 구조적으로 계층 설계 가능
- ✅ `super()`로 상위 생성자 호출 & 필드 초기화
- ✅ `@Override`로 기능을 자식 관점에서 변경 가능
- ✅ `protected`는 자식이 상속받아 직접 사용 가능한 범위
- ✅ 실무에서도 다형성 및 계층 분리 설계에 필수

---

### 🧪 실습 미션

> 🎯 목표: 영어 클래스명 기반으로 실전처럼 상속 구조 구성해보기
> 
1. `Appliance` 클래스: `brand` 필드, `powerOn()` → `"Brand X appliance is on."`
2. `Washer` 클래스: `capacity` 필드 추가, `powerOn()` 오버라이딩
3. `SmartWasher` 클래스: `wifiEnabled` 필드 추가, `powerOn()` 오버라이딩
4. 각 생성자 `super()`로 연결, `powerOn()`은 `super.powerOn()` 포함하여 출력 구성
5. 실행 클래스 `ApplianceExample.java`에서 `SmartWasher` 생성 후 `powerOn()` 호출