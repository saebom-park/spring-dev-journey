# [OOP-5단계] 다형성과 형변환

> “하나의 타입으로 여러 객체를 유연하게 다루고 싶을 땐?”
> 

> 👉 바로 다형성(Polymorphism) 을 사용해서
> 
> 
> 상위 타입 하나로 **여러 자식 객체를 통합 관리**하는 방법을 익히자!
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| 다형성 (Polymorphism) | 부모 타입 하나로 다양한 자식 객체를 다룰 수 있는 성질 |
| 업캐스팅 (Upcasting) | 자식 → 부모로 형 변환 (`Admin` → `Member`) |
| 다운캐스팅 (Downcasting) | 부모 → 자식으로 형 변환 (`Member` → `Admin`) |
| instanceof | 다운캐스팅 전, 안전하게 타입 확인하는 키워드 |
| 실행 흐름 | 메서드는 자식에서 오버라이딩했다면 **자식 기준으로 실행됨** |

---

### 🧾 예시 코드

```java
// 상위 클래스
class Member {
    public void showRole() {
        System.out.println("👤 일반 회원입니다.");
    }
}

// 자식 클래스
class Admin extends Member {
    @Override
    public void showRole() {
        System.out.println("🛠️ 관리자입니다.");
    }

    public void manageSystem() {
        System.out.println("시스템을 관리합니다.");
    }

    public static void main(String[] args) {
        Member m = new Admin(); // 업캐스팅
        m.showRole(); // 🛠️ 관리자입니다. ← 오버라이딩된 메서드 호출

        // m.manageSystem(); // ❌ 오류: Member 타입에는 없음

        if (m instanceof Admin) {
            Admin admin = (Admin) m; // 다운캐스팅
            admin.manageSystem(); // 시스템을 관리합니다.
        }
    }
}
```

---

### 📌 포인트 요약

- 다형성은 상속 구조에서 **하위 객체를 상위 타입으로 참조할 수 있는 성질**
- **업캐스팅**은 자식 객체를 부모 타입 변수로 담는 것 (자동)
- **다운캐스팅**은 부모 타입 변수를 자식 타입으로 다시 꺼내는 것 (명시적)
- `instanceof`로 **안전하게 타입 확인 후** 다운캐스팅하는 습관 필수
- 실행 흐름은 항상 **자식 클래스의 오버라이딩된 메서드가 우선**

---

### 🧪 실습 미션

> 🎯 목표: 다형성 + 업/다운캐스팅 구조 직접 구현해보기
> 
1. `Shape` 클래스: `draw()` 메서드 → `"도형을 그립니다."`
2. `Circle`, `Rectangle` 클래스: `draw()` 오버라이딩
3. 각 클래스에만 존재하는 고유 메서드 추가
    - `Circle`: `radiusInfo()` → `"반지름은 5cm입니다."`
    - `Rectangle`: `angleInfo()` → `"직각을 이룹니다."`
4. `Shape[]` 배열에 다양한 자식 객체들을 담은 뒤,
5. 반복문에서 `draw()` 호출 → `instanceof`로 다운캐스팅 후 고유 메서드 호출