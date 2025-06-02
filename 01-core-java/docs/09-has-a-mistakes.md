# [9단계] 실수노트

---

### 😅 실수 1 — void 메서드를 println()에 사용

```java
System.out.println("회사명: " + company.showCompany()); // ❌ 오류 발생
```

✅ 해결 방법 1: 메서드에서 직접 출력

```java
System.out.println("회사명:");
company.showCompany();
```

✅ 해결 방법 2: 문자열을 반환하도록 변경

```java
public String getCompanyInfo() {
    return companyName + " 위치: " + location;
}
System.out.println("회사명: " + company.getCompanyInfo());
```

---

### 😅 실수 2 — 포함된 객체가 null인데 메서드 호출

```java
Employee employee1 = new Employee();
employee1.empName = "봄이";
employee1.showEmployee(); // ❌ NullPointerException
```

✅ 해결 방법:

```java
employee1.company = company1;
employee1.showEmployee();
```

---

### 😅 실수 3 — 메서드 이름 오타

```java
employee1.showEmplyee(); // ❌ 오타
```

✅ 정답:

```java
employee1.showEmployee(); // ✅
```

---

### 😅 실수 4 — 하나의 파일에 public class 여러 개 선언

```java
public class Company { ... }
public class Employee { ... } // ❌ 또 public 사용!
public class Main { ... }
```

✅ 해결 방법:

```java
class Company { ... }
class Employee { ... }
public class Main { ... } // 파일명은 Main.java
```

---

### 📌 포인트 요약

- `void` 메서드는 출력용으로 println에 직접 못 씀
- 포함된 객체가 `null`일 경우 메서드 호출 시 오류 발생
- 오타는 철자 하나만 달라도 컴파일 에러
- 자바는 `.java` 파일당 `public class` 하나만 허용