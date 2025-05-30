# [9단계] 봄이의 실수 노트 — 포함관계에서 자주 하는 실수 총정리

> 포함관계에서는 단순한 구조지만
> 
> 
> `null`, `void`, `오타`, `파일 규칙` 등
> 
> **자주 틀리는 요소들이 많아!**
> 
> 실수한 부분은 "실력의 재료"가 돼. 🌱
> 

---

### ❗ 1️⃣ void 메서드를 `println()`에 사용한 실수

```java
System.out.println("회사명: " + company.showCompany()); // ❌ 오류 발생
```

### 🔍 왜 안 돼?

- `showCompany()`는 `void` 메서드 → **값을 반환하지 않음**
- `System.out.println()`은 문자열을 기대하는데 `void`는 **없음**
- 결과적으로 `"문자열" + void` 형태가 돼서 **컴파일 오류 발생**

### ✅ 해결 방법 1: 메서드에서 직접 출력

```java
System.out.println("회사명:");
company.showCompany(); // 내부에서 println 수행
```

### ✅ 해결 방법 2: `String`을 반환하는 메서드로 변경

```java
public String getCompanyInfo() {
    return companyName + " 위치: " + location;
}

System.out.println("회사명: " + company.getCompanyInfo());
```

---

### ❗ 2️⃣ 포함된 객체가 null인데 메서드를 호출한 실수

```java
Employee employee1 = new Employee();
employee1.empName = "봄이";
employee1.showEmployee(); // ❌ NullPointerException
```

### 🔍 왜 안 돼?

- `employee1.company`가 아직 아무것도 연결되지 않음 (`null`)
- 내부에서 `company.showCompany()` 호출 → **null에서 메서드 호출 불가**

### ✅ 해결 방법

```java
employee1.company = company1; // 먼저 객체 연결 필수!
employee1.showEmployee();     // 이제 정상 작동!
```

---

### ❗ 3️⃣ 메서드 이름 오타

```java
employee1.showEmplyee(); // ❌ 오타!
```

### ✅ 해결 방법

```java
employee1.showEmployee(); // ✔️ 정확한 철자
```

> 💡 자바는 대소문자, 철자, 스펠링 하나만 틀려도 컴파일 에러!
> 
> 
> 자동완성 기능을 적극 활용하자! 😎
> 

---

### ❗ 4️⃣ 하나의 파일에 `public class` 여러 개 선언

```java
public class Company { ... }
public class Employee { ... } // ❌ 또 public 사용!
public class Main { ... }
```

### 🔍 왜 안 돼?

- 자바는 **한 파일에 `public class`는 하나만 허용**
- 파일명은 그 `public class` 이름과 일치해야 함

### ✅ 해결 방법

- 클래스를 **파일별로 분리**하거나
- 한 파일에 쓸 땐 하나만 `public`, 나머진 생략

```java
class Company { ... }
class Employee { ... }
public class Main { ... } // 파일명은 Main.java
```

---

### ✅ 한 줄 요약

> 포함관계에서는 구조보다도
> 
> 
> **`null` 체크, `void` 반환, 오타, 파일 규칙**이 더 중요할 수 있어!
> 
> 실수는 곧 경험이고, 경험이 곧 실력이야. 🧡
>