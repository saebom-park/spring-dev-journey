# [14-1단계] 실수노트

> 💻 실습 코드: Member.java
> 

---

### 😅 실수 1 — 초기 금액이 두 번 더해짐

```java
Member m1 = new Member("봄이", 1000);
m1.spend(m1.personalSpent); // ❌ 생성자에서 personalSpent를 넣은 후, 또 spend에 전달
```

✅ 정답:

```java
// 방법 1: 생성자에서 바로 spend 호출
Member(String name, int firstSpent) {
    this.name = name;
    spend(firstSpent);
}

// 또는 방법 2: 생성자에서는 소비 처리 없이, 외부에서만 처리
Member(String name) {
    this.name = name;
}
...
Member m1 = new Member("봄이");
m1.spend(1000);
```

📌 **설명**:

- 생성자에서 `personalSpent`에 값을 먼저 넣은 뒤,
    
    다시 같은 값을 `spend(...)`에 넘겨서 **두 번 합산되는 현상**이 발생함
    
- `spend()`가 개인 소비와 총 소비를 동시에 처리하기 때문에
    
    **중복 호출 없이 해당 메서드만으로 통일해야 오류를 피할 수 있음**
    

---

### 📌 요약 포인트

- 같은 값이 두 번 더해지는 흐름이 없는지 항상 체크하기
- 소비 누적 로직은 `spend()` 하나에만 맡기고,
    
    **personalSpent에 직접 할당하지 않기**
    
- static 변수와 instance 변수가 동시에 움직일 땐
    
    **값 증가 위치를 엄격히 구분하기**