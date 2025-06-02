# [9단계] 추가 개념 정리

### 💡 핵심 개념 요약

| 주제 | 설명 |
| --- | --- |
| 포함 관계 (has-a) | 클래스 안에 다른 클래스를 필드로 포함하는 구조 |
| 생명주기 주의 | 포함된 객체는 `new`로 생성되기 전까지 `null` 상태 |
| NullPointerException | null 상태 객체의 메서드/필드에 접근하면 발생 |
| public class 규칙 | 한 파일에 `public class`는 하나만, 파일명과 동일해야 함 |

---

### 🧾 예시 코드 1 — 포함관계에서 NullPointerException 발생

```java
class Member {
    Address address;

    void showInfo() {
        address.showAddress(); // ❌ NullPointerException 발생
    }
}

public class Main {
    public static void main(String[] args) {
        Member m = new Member();
        m.showInfo(); // 주소 객체가 없어서 오류 발생
    }
}
```

---

### 🧾 예시 코드 2 — 객체 생성 후 안전한 사용

```java
Member m = new Member();
m.address = new Address();
m.address.city = "서울";
m.address.street = "강남대로";
m.showInfo(); // ✅ 정상 출력
```

---

### 🧾 예시 코드 3 — null 체크 후 안전한 호출

```java
void showInfo() {
    System.out.println("이름: " + name);
    System.out.println("나이: " + age);

    if (address != null) {
        System.out.print("주소: ");
        address.showAddress();
    } else {
        System.out.println("주소 정보 없음");
    }
}
```

---

### 📌 포인트 요약

- 포함관계로 선언된 필드는 초기값이 `null`
- 사용 전 반드시 `new` 또는 외부 객체 주입 필요
- 실무에서는 항상 `null` 체크 로직 필수
- 클래스 내부의 `void` 메서드는 출력용으로만 사용하고, 반환값을 기대하지 않기

---

## 📦 자바 파일 구조 규칙

---

### 🧾 예시 코드 4 — 파일별 public class 하나만 존재

```java
// Address.java
public class Address {
    String city;
    String street;
}
```

```java
// Member.java
public class Member {
    String name;
    Address address;
}
```

```java
// Main.java
public class Main {
    public static void main(String[] args) {
        ...
    }
}
```

---

### 🧾 예시 코드 5 — 하나의 파일에 여러 클래스 작성 시

```java
class Address {
    String city;
    String street;
}

class Member {
    String name;
    Address address;
}

public class Main {
    public static void main(String[] args) {
        ...
    }
}
```

---

### 📌 포인트 요약

- `public class`는 **한 파일당 1개만 허용**
- `public class`의 **이름은 파일명과 정확히 일치해야 함**
- 하나의 파일에 여러 클래스 작성 시, 나머지는 `public` 생략