# [OOP-6단계] 추상화 설계

> “모든 기기는 켜질 수 있어.
> 
> 
> 그런데 전원이 켜지는 방식은 다 다르지?”
> 

👉 이런 **공통 동작을 강제하고**,

**세부 구현은 자식 클래스에 맡기고 싶을 때**

→ 바로 **추상 클래스와 추상 메서드**를 써야 해!

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| `abstract class` | 인스턴스 생성 불가, 상속 전용 클래스 |
| `abstract method` | 본문 없이 선언만 → 자식 클래스에서 반드시 구현 |
| 구현 강제 | 자식 클래스는 추상 메서드를 무조건 오버라이딩 |
| 다형성 가능 | 추상 클래스도 부모 타입으로써 다형성 가능 |
| 실무 사용 | 공통 인터페이스 강제, 템플릿 역할 |

---

### 🧾 예시 코드

```java
abstract class Device {
    public abstract void powerOn(); // 추상 메서드
}

class Laptop extends Device {
    @Override
    public void powerOn() {
        System.out.println("🔋 Laptop is powering on with battery.");
    }
}

class Desktop extends Device {
    @Override
    public void powerOn() {
        System.out.println("🔌 Desktop is powering on with AC power.");
    }

    public static void main(String[] args) {
        Device[] devices = {new Laptop(), new Desktop()};
        for (Device d : devices) {
            d.powerOn(); // 각각 자식 클래스의 구현이 실행됨
        }
    }
}
```

---

### 📌 포인트 요약

- 추상 클래스는 new로 직접 만들 수 없음
- 추상 메서드는 자식 클래스에서 반드시 오버라이딩
- 상속받은 자식들은 다형성으로 묶어 관리 가능
- 실무에서는 “**공통 기능은 설계로 강제하고, 세부는 각자 구현**”이 필요할 때 자주 사용

---

### 🧪 실습 미션 (클래스명 충돌 없는 버전)

1. `Phone` 추상 클래스: `powerOn()` 추상 메서드
2. `SmartPhone`, `FeaturePhone`: 각각 `powerOn()` 오버라이딩
3. 고유 메서드 추가:
    - `SmartPhone`: `runApp()`
    - `FeaturePhone`: `useKeypad()`
4. `Phone[]` 배열 → 반복문으로 `powerOn()` 실행
5. `instanceof` 분기 후 각 고유 메서드 실행