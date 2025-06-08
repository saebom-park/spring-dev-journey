# [14-3단계] static 심화 기법

> 🧠 "객체 생성을 막고, 클래스 이름으로만 동작하게 하려면?"
> 
> 
> 👉 바로 `private 생성자 + static 메서드` 조합이야!
> 

---

### 💡 핵심 개념 요약

| 구분 | 설명 | 예시 |
| --- | --- | --- |
| static 블록 | 클래스가 처음 로드될 때 1회 실행되는 영역 | static { ... } |
| static factory method | 객체를 직접 생성하지 않고 static 메서드로 반환 | `of()`, `valueOf()`, `from()` |
| private 생성자 | 외부에서 new로 객체 생성 못하게 막음 | 싱글톤, 유틸리티 클래스 |
| Math 클래스 | 대표적인 static 전용 유틸 클래스 | `Math.random()`, `Math.pow()` |

---

### 🧾 예시 코드

### ✅ 1. static 초기화 블록 예제

```java
public class InitExample {
    static int count;

    static {
        System.out.println("클래스가 처음 로드될 때 실행됨!");
        count = 100;
    }

    public static void showCount() {
        System.out.println("count = " + count);
    }

    public static void main(String[] args) {
        InitExample.showCount();  // 클래스 로딩 시 static 블록 실행
    }
}
```

### ✅ 2. private 생성자 + static method

```java
public class MemberFactory {
    private MemberFactory() {
        // 외부에서 생성 못함!
    }

    public static Member create(String name) {
        Member m = new Member();
        m.name = name;
        return m;
    }
}

class Member {
    String name;
}
```

---

### 📌 포인트 요약

- **static 블록**은 클래스 로딩 시 한 번만 실행됨 → 주로 초기값 설정에 사용
- **private 생성자 + static method** → 객체 생성을 제어하고, 정적 메서드로만 접근 가능하게 함
- Spring에서도 `of()`, `getInstance()` 같은 static 생성 방식 자주 사용
- **유틸 클래스나 싱글톤 패턴 구현**할 때 꼭 쓰이는 패턴

---

### 🧪 실습 미션

### ✔️ 실습 미션: `AdminUser` 객체를 static 방식으로만 생성하기

1. `AdminUser` 클래스 만들기
    - `String name`, `String role` 멤버 변수
    - 생성자는 `private`으로 막기
2. static 메서드 `create(String name)` 작성
    - `role`은 자동으로 `"ADMIN"`으로 설정
3. `AdminUserMain`에서 객체 생성하고, 정보 출력하기
    - 객체는 `AdminUser.create("봄이")`처럼 생성해야 함
4. 직접 `new AdminUser()`는 불가능해야 함!

---

## 🧪 실습 미션 2: **"기본 생성자 vs 매개변수 생성자 비교하기"**

### 📘 실습 목표

> ❓ "객체를 만들 때 이름을 꼭 줘야 할까?"
> 
> 
> → **두 가지 방식으로 비교해보고 직접 판단해보자!**
> 

---

### 🧩 실습 조건

### ✅ **버전 1: 기본 생성자 사용**

1. `BasicAdminUser` 클래스 만들기
    - `String name` 필드 선언
    - `private BasicAdminUser()` 기본 생성자 사용
    - static 메서드 `create(String name)` 만들기 → name 수동 설정
2. `BasicAdminUserMain` 클래스에서 테스트
    - `create("봄이")`로 객체 생성
    - `printStatus()`에서 이름 출력

---

### ✅ **버전 2: 매개변수 생성자 사용**

1. `BetterAdminUser` 클래스 만들기
    - `String name` 필드 선언
    - `private BetterAdminUser(String name)` 생성자 작성
    - static 메서드 `create(String name)`에서 name을 생성자에 바로 전달
2. `BetterAdminUserMain` 클래스에서 테스트
    - `create("온이")`로 객체 생성
    - `printStatus()`에서 이름 출력

---

### 🧷 비교 포인트

- `BasicAdminUser`는 name을 **생성 후에 따로 설정**
- `BetterAdminUser`는 name을 **생성 시점에 강제 입력**
- 어떤 방식이 더 안정적이고 실무에 적합한지 판단해보자!

---

### ✅ 출력 예시 (둘 다 정상 작동해야 함)

```
봄이 관리자 계정이 생성되었습니다.
온이 관리자 계정이 생성되었습니다.
```