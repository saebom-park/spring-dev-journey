# [OOP-6단계] 실수노트

> 💻 실습 코드: DeviceExample.java
> 

---

### 😅 실수 1 — 추상 클래스 이름 혼동

✅ 정답:

```java
public class SmartPhone extends Phone  // ✅ 실제 추상 클래스 이름으로 수정!
```

📌 **설명**:

- 실습에서 추상 클래스를 `Phone`으로 정의했는데, 자식 클래스가 실수로 `Device`를 상속함
- 컴파일 오류 발생! 클래스명이 많아질수록 반드시 상속 관계를 꼼꼼히 확인해야 해

---

### 😅 실수 2 — Scanner 입력 처리 중 줄바꿈 이슈 발생 가능성

```java
Scanner scanner = new Scanner(System.in);
String input = scanner.next();   // 단어 입력 (공백 기준)
String line = scanner.nextLine(); // 줄 전체 입력

// next() 다음에 nextLine() 호출 시 입력 누락 이슈 가능성 있음
```

📌 **설명**:

- `next()`는 공백 기준으로 한 단어만 읽고, 줄바꿈 문자를 버리지 않음
- 바로 다음에 `nextLine()`을 호출하면 **빈 줄이 읽히는 버그 발생 가능**
- 해결 방법: `scanner.nextLine();`으로 줄바꿈 문자 먼저 제거하고 다음 입력을 받자!