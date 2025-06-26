# Day05 | 질문노트

### ❓ Q1. 배열 선언할 때 크기만 정하고 값은 나중에 넣을 수 있나요?

- 배열 선언 시 `new int[5]`처럼 크기만 지정하면
    
    **기본값(0)** 으로 초기화된 공간이 생성됨
    
- 이후 `arr[0] = 10` 식으로 값 할당 가능

```java
int[] arr = new int[3];
arr[0] = 10;
arr[1] = 20;
```

---

### ❓ Q2. Scanner로 숫자 아닌 값을 입력하면 어떤 예외가 발생하나요?

- `Scanner.nextInt()`에 문자가 들어오면
    
    **`InputMismatchException`** 예외 발생
    
- 사용자 입력이 불안정할 때 `try-catch`로 감싸야 안전

```java
try {
    int num = scanner.nextInt();
} catch (InputMismatchException e) {
    System.out.println("숫자를 입력해야 해요!");
}
```

---

### ❓ Q3. 문자열을 쪼개서 숫자 2개를 나누려면 어떻게 하나요?

- 문자열을 **`split(" ")`** 로 나누고
    
    각 토큰을 숫자로 변환하면 됨
    

```java
String input = "10 20";
String[] parts = input.split(" ");
int a = Integer.parseInt(parts[0]);
int b = Integer.parseInt(parts[1]);
```

---

### ❓ Q4. 문자열을 정수로 바꾸는 방법은?

- `Integer.parseInt("123")` 사용
- `Double.parseDouble()`, `Long.parseLong()`도 동일 패턴

```java
String s = "42";
int num = Integer.parseInt(s); // 42
```

---

### ✅ 요약 표

| 개념 | 설명 |
| --- | --- |
| `new int[5]` | 크기만 지정하고 나중에 값 할당 |
| `InputMismatchException` | Scanner로 숫자 아닌 값 입력 시 발생 |
| `split(" ")` | 문자열 공백 기준으로 쪼개기 |
| `Integer.parseInt()` | 문자열을 정수로 변환 |