# [REVIEW-5-3] 실수노트

> 💻 실습 코드: Delivery.java, DeliveryServiceImpl.java, DeliveryController.java

---

### 😅 실수 1 — @RequestBody DTO에 기본 생성자가 없어 400 Bad Request 발생

```java
public class DeliveryRequestDto {
    private String customerName;
    public DeliveryRequestDto(String customerName) { ... }
}
```

✅ 정답:

```java
public DeliveryRequestDto() {} // 기본 생성자 추가
```

📌 **설명**:

- Spring에서 @RequestBody를 통해 JSON을 DTO로 바인딩하려면 기본 생성자가 반드시 필요함
- Jackson이 내부적으로 new 생성 후 setter로 값 주입하는 방식 사용

---

### 😅 실수 2 — JSON 배열 형식 없이 bulk-fail 요청

```json
{
  "customerName": "연이"
},
{
  "customerName": "솔이"
}
```

✅ 정답:

```json
[
  { "customerName": "연이" },
  { "customerName": "솔이" }
]
```

📌 **설명**:

- List<Dto>를 @RequestBody로 받을 땐 반드시 JSON 배열 (`[]`) 로 감싸야 함
- JSON 객체 2개를 쉼표로 나열한 구조는 유효하지 않음

---

### 📌 요약 포인트

- `@RequestBody` DTO에는 **기본 생성자 필수**
- 리스트 요청은 **JSON 배열([])** 로 감싸야 Spring에서 파싱 가능
- 실험 API에서도 `Content-Type: application/json` 설정이 정확히 필요