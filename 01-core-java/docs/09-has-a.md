# [9단계] 클래스 간 관계: 포함 (has-a)

> 클래스 안에 또 다른 클래스를 포함시켜서
> 
> 
> 구성요소나 협력 관계를 표현하는 구조를 배워보자! 🌱
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| 포함 관계 (has-a) | 어떤 클래스가 다른 클래스를 **필드로 가지고 있음** |
| 객체 속 객체 | 클래스 안에 또 다른 객체가 포함됨 (`class A { B b; }`) |
| 사용 이유 | 구성요소 표현, 정보 분리, 협력 구조 설계 |
| 예시 | `Member` 클래스 안에 `Address` 객체 포함 |

---

### 🧾 예시 코드

```java
java
복사편집
// 주소 클래스
public class Address {
    String city;
    String street;

    void showAddress() {
        System.out.println(city + " " + street);
    }
}

// 회원 클래스 (주소를 포함)
public class Member {
    String name;
    int age;
    Address address; // 포함 관계 (has-a)

    void showInfo() {
        System.out.println("이름: " + name);
        System.out.println("나이: " + age);
        System.out.print("주소: ");
        address.showAddress(); // 포함된 객체의 메서드 사용
    }
}

// 메인 클래스
public class Main {
    public static void main(String[] args) {
        Address addr = new Address();
        addr.city = "서울";
        addr.street = "강남대로";

        Member member = new Member();
        member.name = "봄이";
        member.age = 27;
        member.address = addr; // 포함된 객체를 연결

        member.showInfo();
    }
}

```

---

### 📌 포인트 요약

- `Address` 클래스는 **독립적으로 존재 가능**
- `Member` 클래스는 `Address`를 **필드로 포함**
- **다른 객체의 기능을 가져다 쓸 수 있음** → `member.address.showAddress()`
- 실무에서 DTO, Entity에서 정보 구조화할 때 자주 사용됨

---

### 🧪 실습 미션

> ✅ 클래스를 2개 만들고 포함 관계를 직접 구현해보자!
> 
1. `Company` 클래스 만들기
    
    → 필드: `companyName`, `location`, 메서드: `showCompany()`
    
2. `Employee` 클래스 만들기
    
    → 필드: `empName`, `Company company`, 메서드: `showEmployee()`
    
3. `Main` 클래스에서:
    - `Company` 객체 생성 후 필드 설정
    - `Employee` 객체 생성 후 이름/회사 연결
    - `showEmployee()` 실행 시 회사 정보까지 함께 출력되게 만들기!

---

## ❔ Q&A 포인트 정리

### ❓ `void showAddress()`는 왜 `public` 안 붙였을까?

- **접근 제어자가 없으면** → default 접근자
- default는 **같은 패키지 안에서만 접근 가능**
- 예제에서는 간단히 보이려고 생략했지만
    
    실무에선 꼭 `public`을 명시하자! 👀
    

| 접근 제어자 | 외부 접근 가능 여부 |
| --- | --- |
| public | ✅ 어디서든 가능 |
| default | ❌ 다른 패키지에서는 불가 |

---

### ❓ 패키지(package)란?

| 개념 | 설명 |
| --- | --- |
| 패키지 | 자바 클래스들을 **폴더처럼 묶는 구조** |
| 같은 패키지 | **같은 폴더에 있는 클래스들**끼리 |
| default 접근자 | 같은 패키지끼리만 접근 가능 |

→ 클래스가 `public`이 아니면, **다른 패키지에서 import조차 불가**

→ 내부 메서드가 `default`면, **외부에서 접근도 불가**

---

### 📌 정리 예시

```java
java
복사편집
package com.example;

public class Member {
    public String name = "봄이";

    void defaultMethod() {
        System.out.println("default method");
    }

    public void publicMethod() {
        System.out.println("public method");
    }
}

```

```java
java
복사편집
package com.other;

import com.example.Member;

public class Main {
    public static void main(String[] args) {
        Member m = new Member();

        System.out.println(m.name);     // ✅ 가능
        m.publicMethod();               // ✅ 가능
        m.defaultMethod();              // ❌ 오류 (다른 패키지에서 default 접근 불가)
    }
}

```

---

## ✅ 한 줄 요약

> 포함 관계(has-a)는
> 
> 
> 객체 안에 또 다른 객체가 포함되어 협력하는 구조이며,
> 
> **캡슐화, 구조 분리, 코드 재사용에 아주 중요한 개념**이야!
>