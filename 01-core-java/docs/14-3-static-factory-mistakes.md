# [14-3단계] 실수노트

> 💻 실습 코드: FactoryMember.java
> 

---

### 😅 실수 1 — 기존 클래스와 이름이 겹쳐 컴파일 에러 발생

```
public class Member { ... } // 다른 스텝에서 사용한 클래스명
```

✅ 정답:

```
public class FactoryMember { ... } // 클래스 이름 변경
```

📌 **설명**:

- 동일 프로젝트 내 `Member.java`가 이미 존재해서 컴파일 시 충돌 발생
- 새로운 클래스는 **의미를 구분하기 위해 별도 이름 사용**이 안전함

---

### 😅 실수 2 — 기본 생성자 호출 시 매개변수가 없다는 걸 놓침

```
FactoryMember m = new FactoryMember(name); // ❌ 컴파일 에러 발생
```

✅ 정답:

```
FactoryMember m = new FactoryMember(); // ✅ 기본 생성자 호출
m.name = name;
```

📌 **설명**:

- `FactoryMember` 클래스에 매개변수 생성자가 없기 때문에 `new FactoryMember(name)`는 에러 발생
- 기본 생성자를 먼저 호출한 뒤 필드를 직접 설정해야 함

---

### 😅 실수 3 — 클래스 자체를 `private class`로 정의함

```
private class MemberFactory { ... } // ❌ 외부 접근 불가
```

✅ 정답:

```
public class MemberFactory { ... } // ✅ 외부 접근 허용
```

📌 **설명**:

- `private class`는 **외부 클래스에서는 접근 불가**하므로, 정적 메서드 호출조차 막힘
- 생성자만 `private`으로 설정하는 게 핵심이며, 클래스 자체는 `public`이어야 함

---

### 📌 요약 포인트

- **클래스 이름 중복**은 피하고, 구체적인 이름으로 분리할 것
- **생성자 호출 형식**은 선언된 형태와 맞춰야 함 (기본 vs 매개변수)
- 클래스 전체를 `private`으로 만들면 외부 접근 자체가 막히므로 주의할 것
- 실수는 거의 다 **접근 제어자 위치나 클래스 구성 방식 오해**에서 발생함