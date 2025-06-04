# [13-2단계] 질문노트: 예외 처리 (ThrowExample.java)

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: `ThrowExample.java` 실습 기준)
> 

---

### 💡 1. `System.in` / `System.out` / `Scanner`

| 질문 | 답변 요약 |
| --- | --- |
| `System.in`은 왜 in만 써? | 입력 장치(키보드) 자체를 넘기기 위한 객체. `new Scanner(System.in)`에 넘겨주는 용도. |
| `System.out.println()`이랑 `System.out` 차이는? | `System.out`은 출력 도구 객체, `.println()`은 명령 메서드 |
| `System.out("메롱");` 하면? | ❌ 에러! `System.out`은 함수가 아니라 객체이기 때문에 호출 불가 |
| `nextString()` 있나요? | ❌ 없음. 대신 `next()`나 `nextLine()` 사용 |
| `scanner.nextInt()`에 문자열 넘기면? | 에러남! 타입에 맞는 입력만 받아야 함 |

---

### 💡 2. 예외 처리 구조 (`try-catch`, `throw`, `getMessage()` 등)

| 질문 | 답변 요약 |
| --- | --- |
| `ArithmeticException` 내가 만든 적 없는데? | 자바에서 미리 정의한 예외 클래스. 자주 발생하는 에러는 자바가 자동 감지해서 이 클래스를 생성해 던짐 |
| `throw new ArithmeticException(...)`는 왜 new야? | `new Dog()`처럼 객체를 생성하는 것과 동일함. 예외 객체를 생성해 던짐 |
| `catch (ArithmeticException e)`는 뭐야? | 던져진 예외 객체를 `e`라는 변수에 담아서 catch 블럭으로 전달받음 |
| `throw new ...` 안 했는데 예외 뜨면? | 자바 JVM이 자동으로 예외 객체를 생성해서 던져줌 (`/ by zero` 같은 기본 메시지 포함) |
| `ArithmeticException` 안에 if(분모가 0)... 돼있어? | ❌ 아님! 그런 판단은 JVM이 함. 클래스는 단지 메시지와 상태만 담는 그릇 |
| `getMessage()` 직접 정의 안 했는데 왜 돼? | `Throwable` 클래스에 이미 정의돼 있음. 모든 예외 클래스가 상속받음 |
| `getMessage()`는 void 아닌가요? | ❌ `String`을 return하는 메서드여야 `println()`에 넣을 수 있음. `void`면 컴파일 에러 |

---

### 💡 3. 봄이의 관찰 기반 실험 결과

| 실험 내용 | 결과 |
| --- | --- |
| `throw new ...` 주석처리하고 `a / 0` 실행 | 자바가 자동으로 `/ by zero` 메시지 포함된 예외 생성해서 던짐 |
| `e.getMessage()`로 출력한 메시지 | 자바가 생성한 메시지 출력됨 (`/ by zero`) |

---

### 💡 4. `import java.util.Scanner;` 속의 `util`은 뭐야?

| 질문 | 답변 요약 |
| --- | --- |
| `util`은 골프채야...? | ❌ 골프채 아님! `util`은 `utility(유틸리티)`의 줄임말로, 자바에서 자주 쓰는 도구들을 모아놓은 “도구 상자” 역할 |
| `java.util`은 뭘까? | `java` 패키지 안의 `util` 하위 패키지. `Scanner`, `Date`, `Random` 등 자주 쓰는 클래스가 들어 있음 |
| 왜 `import java.util.Scanner`라고 써야 해? | Scanner는 `util` 안에 있는 클래스이기 때문. 자바에서 다른 패키지의 클래스를 쓰려면 `import` 해줘야 함 |

---

### 🌱 정리 키워드

- 예외는 객체다 → 생성해서 던진다 → 변수로 받아서 처리한다
- `throw = 던지기`, `catch = 받기`, `getMessage = 메시지 보기`
- `Throwable` 클래스가 모든 예외의 조상이고, 주요 메서드 제공