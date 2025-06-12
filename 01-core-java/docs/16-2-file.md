# [16-2단계] File 클래스로 파일 정보 다루기

> “자바로 폴더 안에 있는 파일 확인하거나,
> 
> 
> 파일이 존재하는지 검사하려면 어떻게 하지?”
> 
> 👉 바로 `File` 클래스가 필요해!
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| `File` 클래스 | 파일/디렉토리의 경로, 존재 여부, 속성 등을 다루는 클래스 |
| `exists()` | 해당 경로에 실제로 존재하는지 확인 |
| `isFile()` / `isDirectory()` | 파일인지 폴더인지 구분 |
| `length()` | 파일 크기 (bytes 단위) |
| `getAbsolutePath()` | 절대 경로 가져오기 |

---

### 🧾 예제 코드

```java
import java.io.File;

public class FileExample {
    public static void main(String[] args) {
        File file = new File("test.txt");

        if (file.exists()) {
            System.out.println("파일이 존재합니다.");
            System.out.println("절대 경로: " + file.getAbsolutePath());
            System.out.println("파일 크기: " + file.length() + " bytes");

            if (file.isFile()) {
                System.out.println("이건 파일입니다.");
            } else if (file.isDirectory()) {
                System.out.println("이건 폴더입니다.");
            }
        } else {
            System.out.println("파일이 존재하지 않습니다.");
        }
    }
}
```

---

### 📌 포인트 요약

| 항목 | 설명 |
| --- | --- |
| `new File("파일경로")` | 파일 또는 폴더를 나타내는 객체 생성 (실제 생성 X) |
| `exists()` | 실제로 파일이 존재하는지 검사 |
| `getAbsolutePath()` | 절대 경로 확인 |
| `length()` | 크기 확인 (폴더는 0으로 나옴) |
| `isFile()` / `isDirectory()` | 타입 구분 |

---

### 🧪 실습 미션

> FilePractice.java 파일을 만들어 아래 기능을 구현해보자!
> 

```
✅ "sample.txt"라는 파일이 현재 경로에 존재하는지 확인
✅ 존재한다면: 절대 경로, 크기, 파일인지 폴더인지 출력
✅ 존재하지 않으면: "파일이 없습니다." 출력
```