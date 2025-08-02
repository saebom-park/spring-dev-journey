# [Review-4-3] 실수노트

> 💬 실습 코드: ProductMapper.xml, ProductServiceImpl.java
> 

---

### 😅 실수 1 — DOCTYPE 오타

```xml
<!DOCTYPE mapper
        PUBLID "-//mybatis.org//DTD Mapper 3.0"

```

✅ 정답:

```xml
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0"

```

📌 **설명**:

- `PUBLID`는 오타로, `PUBLIC`으로 정확히 입력해야 XML 파싱 오류가 발생하지 않음

---

### 😅 실수 2 — association 태그에 id 속성 사용

```xml
<association id="Category" javaType="...">

```

✅ 정답:

```xml
<association property="category" javaType="...">

```

📌 **설명**:

- `<association>`에는 `id`가 아니라 `property` 속성을 써야 필드 매핑이 작동함
- `id`는 resultMap 정의에 사용하는 속성임

---

### 😅 실수 3 — select 태그에 resultMap 누락

```xml
<select id="selectProductsWithCategory">

```

✅ 정답:

```xml
<select id="selectProductsWithCategory" resultMap="ProductResultMap">

```

📌 **설명**:

- resultMap 연결이 없으면 SQL 결과를 DTO로 매핑할 수 없음
- 반드시 `resultMap` 속성을 지정해줘야 동작함

---

### 😅 실수 4 — 메서드 이름 대소문자 불일치

```xml
<select id="SelectProductsWithCategory">

```

✅ 정답:

```xml
<select id="selectProductsWithCategory">

```

📌 **설명**:

- Mapper 인터페이스의 메서드명과 XML의 id는 대소문자까지 100% 일치해야 함
- 일치하지 않으면 MyBatis가 바인딩하지 못함

---

### 📌 요약 포인트

- DOCTYPE 선언은 `PUBLIC` 철자 오류 없이 작성할 것
- `<association>`에는 `property`, `javaType` 속성 필수
- select 태그에 `resultMap` 누락되지 않도록 주의
- select id와 메서드명은 대소문자까지 완전 일치해야 함