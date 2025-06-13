# [16-3단계] 질문노트: 파일 입출력 (WriteFileExample.java)

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: WriteFileExample.java 실습 기준)
> 

---

### 💡 1. 질문 주제 요약

| 질문 | 답변 요약 |
| --- | --- |
| `try (FileWriter writer = new FileWriter(...))` 문법은 왜 `try()`처럼 생겼나요? | try-with-resources 구문이에요. 괄호 안의 자원은 AutoCloseable 인터페이스를 구현하고 있어서 try 종료 시 자동으로 close() 처리돼요. |
| `(line = reader.readLine()) != null`은 무슨 의미인가요? | reader로 한 줄 읽은 결과를 line에 저장하고, 그 값이 null이 아닌 동안 반복하라는 뜻이에요. null이면 더 이상 읽을 줄이 없는 상태예요. |
| 왜 `BufferedReader`에 `FileReader`를 넣나요? | `BufferedReader`는 줄 단위로 효율적으로 읽는 클래스고, 파일을 직접 여는 건 `FileReader`라서 두 클래스를 조합해 사용해요. |
| `File`과 `FileReader`는 어떤 차이가 있나요? | `File`은 파일의 존재/경로/크기 같은 정보만 다루고, `FileReader`는 파일 내용을 문자 단위로 읽는 스트림이에요. |
| `FileWriter`로 두 번 실행했더니 첫 번째 내용이 사라지던데요? | FileWriter는 기본이 덮어쓰기(overwrite) 모드예요. 내용을 추가하려면 `new FileWriter(path, true)`처럼 true를 넣어야 해요. |
| `createNewFile()`은 어디서 실제 파일을 생성하나요? | `new File(...)`은 파일 객체만 만들고, 실제 파일은 `createNewFile()`을 호출해야 생성돼요. |
| 상대 경로로 파일 만들면 어디에 생기나요? | `System.getProperty("user.dir")`로 확인할 수 있는 현재 실행 디렉토리에 생겨요. |

---

### 🌱 정리 키워드

- try-with-resources 문법
- BufferedReader와 FileReader 조합
- 덮어쓰기 vs 추가쓰기 모드
- 파일 객체 생성과 실제 생성의 차이