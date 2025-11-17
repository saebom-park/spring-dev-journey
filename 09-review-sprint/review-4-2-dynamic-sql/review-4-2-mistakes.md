# [REVIEW-4-2] 실수노트

> 💻 실습 코드: ProductMapper.xml

---

### 😅 실수 1 — ORDER BY를 `<where>` 안에 넣음

```xml
<where>
    ...
    ORDER BY ${sortBy} ${sortDir}  <!-- ❌ 여기 있으면 문법 오류 발생 -->
</where>
```

✅ 정답:

```xml
<where>
    ... 조건들 ...
</where>
ORDER BY ${sortBy} ${sortDir}  <!-- ✅ 반드시 WHERE 밖에 위치 -->
```

📌 설명:
- SQL 문법상 ORDER BY는 WHERE 절 바깥에 위치해야 함
- MyBatis는 XML 기반이라 잘못 넣으면 XML 파싱 자체가 실패할 수 있음

---

### 😅 실수 2 — `parameterType`에 패키지명 생략

```xml
<select id="selectFilteredProducts" parameterType="ProductFilterDto">
```

✅ 정답:

```xml
<select id="selectFilteredProducts" parameterType="com.review42.dto.ProductFilterDto">
```

📌 설명:
- `parameterType`은 FQCN(전체 클래스명)으로 적어야 안전함
- 생략 시 MyBatis가 alias로 인식 못하거나 못 찾을 수 있음

---

### 😅 실수 3 — 비교 연산자 XML 이스케이프 누락

```xml
<if test="maxPrice != null">
    AND p.product_price <= #{maxPrice}  <!-- ❌ 오류 발생 -->
</if>
```

✅ 정답:

```xml
<if test="maxPrice != null">
    AND p.product_price &lt;= #{maxPrice}  <!-- ✅ XML 파서가 인식 가능 -->
</if>
```

📌 설명:
- `<`, `>`는 XML 특수 문자라 직접 쓰면 안 됨
- `&lt;`, `&gt;`로 이스케이프 처리해야 함

---

### 📌 요약 포인트

- ORDER BY는 WHERE 밖에 작성해야 함
- parameterType은 전체 경로로 작성해야 MyBatis가 인식함
- XML 비교 연산자는 `<`, `>` 대신 `&lt;`, `&gt;`로 써야 함

