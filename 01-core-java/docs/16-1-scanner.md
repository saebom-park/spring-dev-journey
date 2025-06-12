# [16-1단계] Scanner로 사용자 입력 받기

> “자바에서 사용자에게 ‘이름이 뭐에요?’ 하고 물어보려면?”
> 
> 
> 👉 바로 `Scanner` 클래스를 사용하면 돼!
> 
> 콘솔 입력의 핵심 클래스 ✍️
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| `Scanner` | 콘솔(키보드)로부터 입력 받는 자바 클래스 |
| `new Scanner(System.in)` | 입력을 받을 준비를 하는 구문 |
| `nextLine()` | 한 줄 전체 입력 받기 |
| `next()` | 단어 단위로 입력 받기 (공백 기준) |
| `nextInt()`, `nextDouble()` | 숫자 입력 받을 때 사용 |

---

### 🧾 예제 코드

```java
import java.util.Scanner;

public class ScannerExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("이름을 입력하세요: ");
        String name = sc.nextLine(); // 공백 포함 한 줄 전체 입력
        System.out.println("안녕하세요, " + name + "님!");

        System.out.print("나이를 입력하세요: ");
        int age = sc.nextInt(); // 숫자 입력
        System.out.println("당신은 " + age + "살이군요!");

        sc.close(); // 사용 후 닫기 (습관처럼 익혀두면 좋아!)
    }
}
```

---

### 📌 포인트 요약

| 항목 | 내용 |
| --- | --- |
| `System.in` | 입력 장치 (표준 입력 스트림) |
| `sc.nextLine()` | 엔터 칠 때까지 입력된 **한 줄 전체** 가져옴 |
| `sc.next()` | 공백 기준 **하나의 단어**만 가져옴 |
| `sc.nextInt()` | 숫자(int) 입력 받을 때 사용 |
| `sc.close()` | 메모리 누수 방지를 위해 사용 후 닫는 습관 들이기! |

---

### 🧪 실습 미션

> ScannerPractice.java 파일을 만들어 아래 기능을 구현해보자!
> 

```
✅ 사용자에게 이름과 나이를 입력받고 출력
✅ 사용자에게 사는 도시 이름을 입력받고 출력
```

출력 예시:

```
이름을 입력하세요: 봄이
나이를 입력하세요: 25
사는 도시는 어디인가요? 서울

결과:
[봄이]님은 [25살], [서울]에 살고 있습니다.
```