# [OOP-8단계] 실습 미션 1: 객체 협력 구조 설계 문제 (CourierExample.java)

### 🧩 시나리오

> 📦 택배 시스템을 구현해보자!
> 

### ❗ 요구 사항

- `Customer`는 `Courier`에게 **택배 배송을 요청**한다.
- `Courier`는 **배송 차량(Vehicle)** 을 이용해 **배송을 처리**한다.
- 각 객체는 자신의 역할만 수행하고, 협력을 통해 전체 기능을 완성해야 한다.

---

### ✨ 기대 출력 예시

```
봄이 고객이 택배를 요청합니다.
배달원이 배송 준비를 시작합니다.
배달 차량으로 배송 중입니다.
배송이 완료되었습니다!
```

---

### 🛠️ 설계 조건

- `DeliveryCustomer` 클래스 → `requestDelivery(Courier courier)`
- `Courier` 클래스 → `deliver(Vehicle vehicle)`
- `DeliveryVehicle` 클래스 → `move()`
- `Courier`는 `Vehicle`을 내부에서 직접 소유해야 함 (`Courier` 생성자에서 주입)
- `DeliveryCustomer` 는 `Courier`에게만 메시지를 보내야 함

---

### ✅ 포인트

- 객체 협력의 흐름을 다시 몸으로 익히는 연습
- main에서는 **`Customer → Courier` 한 줄만 호출**하면 흐름이 쭉 이어져야 해
- 출력은 예시처럼 자연스럽고 사람 대화처럼 나오게 해봐