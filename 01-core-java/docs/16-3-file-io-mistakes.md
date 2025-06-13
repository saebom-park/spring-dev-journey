# [16-3단계] 실수노트: 파일 입출력 (WriteFileExample.java)

> 💻 실습 코드: WriteFileExample.java
> 

---

### 😅 실수 1 — FileWriter 덮어쓰기 현상

```java
FileWriter writer = new FileWriter("file.txt");
```

✅ 정답:

```java
FileWriter writer = new FileWriter("file.txt", true);
```

📌 **설명**:

- 기본 FileWriter는 덮어쓰기 모드라 실행할 때마다 기존 파일 내용을 지워요.
- 파일에 내용을 추가하려면 두 번째 인자 `true`로 append 모드를 설정해야 해요.

---

### 😅 실수 2 — new File()만으로 파일이 생성되는 줄 앎

```java
File file = new File("newfile.txt");
```

✅ 정답:

```java
File file = new File("newfile.txt");
file.createNewFile();
```

📌 **설명**:

- `new File(...)`은 실제 파일을 만드는 게 아니라 파일 경로를 지정한 객체를 만드는 작업이에요.
- 디스크에 파일을 생성하려면 반드시 `createNewFile()`을 호출해야 해요.

---

### 📌 요약 포인트

- FileWriter는 append 모드를 명시하지 않으면 항상 덮어씀
- new File()은 경로 객체만 만들고, 실제 파일은 `createNewFile()`이 필요함