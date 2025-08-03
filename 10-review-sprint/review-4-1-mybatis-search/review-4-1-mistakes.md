# [REVIEW-4-1] 실수노트

> 💻 실습 코드: ProductMapper.xml, ProductApiApplication.java

---

### 😅 실수 1 — Spring Boot 3.x에서 MyBatis 설정을 spring 하위에 둔 실수

```yaml
spring:
  mybatis:
    mapper-locations: classpath:mapper/*.xml
```

✅ 정답:

```yaml
mybatis:
  mapper-locations: classpath:mapper/*.xml
```

📌 **설명**:

- Spring Boot 3.x부터는 MyBatis 설정을 `spring` 하위가 아니라 `mybatis:` 최상위에 둬야 한다
- 그렇지 않으면 XML 매퍼 파일을 찾지 못해서 애플리케이션 실행이 실패함

---

### 📌 요약 포인트

- Spring Boot 3.x부터는 MyBatis 설정 키가 변경됨
- `spring.mybatis` → ❌ 인식 안 됨
- `mybatis:` → ✅ 최상위에서 동작함
- 실행 실패 시 `ApplicationContext` 초기화 오류 + `non-zero exit 1` 등으로 확인 가능

