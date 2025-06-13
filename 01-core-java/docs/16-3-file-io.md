# 16-3단계: 파일 읽기 (BufferedReader)

> ✨ “파일 내용을 한 줄씩 읽고 싶을 땐?”
> 
> 
> ➤ 바로 `BufferedReader`의 출동이야!
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| `BufferedReader` | 텍스트 파일을 **한 줄씩** 읽을 수 있는 클래스 |
| `FileReader` | 파일을 문자 기반으로 읽을 수 있게 해주는 클래스 |
| `readLine()` | 한 줄씩 읽는 메서드 (더 이상 읽을 줄이 없으면 `null` 반환) |
| `try-with-resources` | 파일 닫기를 자동으로 해주는 안전한 방법 (`try(...) {}`) |

---

## 🧾 예시 코드: SampleFile.txt 읽기

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderExample {
    public static void main(String[] args) {
        // 읽을 파일 경로를 설정해요
        String filePath = "01-core-java/src/SampleFile.txt";

        // try-with-resources 문법: () 안에 선언한 자원은 자동으로 close() 돼요
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // 파일에서 한 줄씩 읽어서 저장할 변수
            String line;

            // 파일 끝(null)까지 계속 한 줄씩 읽어요
            while ((line = reader.readLine()) != null) {
                // 읽은 한 줄을 화면에 출력해요
                System.out.println(line);
            }

        // 파일이 없거나 읽는 중 에러가 나면 여기로 와요
        } catch (IOException e) {
            // 에러 내용을 화면에 출력해줘요
            System.out.println("파일을 읽는 도중 오류 발생: " + e.getMessage());
        }
    }
}
```

---

## 🧪 실습 미션

1. `SampleFile.txt`에 원하는 내용을 여러 줄 써보자
2. 위 코드를 실행해서 한 줄씩 잘 출력되는지 확인하자
3. 파일 경로가 잘못된 경우도 테스트해보자 (예외 메시지 확인용)