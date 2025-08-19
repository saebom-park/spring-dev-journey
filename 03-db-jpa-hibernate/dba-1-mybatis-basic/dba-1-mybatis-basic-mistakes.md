# [DBA-1단계] 실수노트

> 💻 실습 코드: mybatis-config.xml / BookMapper.xml
> 

---

### 😅 실수 1 — DTD 선언문에서 `PUBLIC` 누락 및 꺾쇠 기호 사용

```xml
<!DOCTYPE configuration "-//mybatis.org//DTD Config 3.0//EN" "<http://mybatis.org/dtd/mybatis-3-config.dtd>">
```

✅ 정답:

```xml
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
```

📌 **설명**:

- `PUBLIC` 키워드는 DTD 선언의 고정 문법이므로 반드시 포함해야 함
- URL 부분에 `<http...>`처럼 꺾쇠를 쓰면 XML 파싱이 실패함
- 이런 실수는 MyBatis 설정 자체가 무시되어 `NullPointerException`으로 이어질 수 있음

---

### 😅 실수 2 — `<dataSource>` 태그의 속성 오타 (`tyle`)

```xml
<dataSource tyle="POOLED">
```

✅ 정답:

```xml
<dataSource type="POOLED">
```

📌 **설명**:

- `tyle` → `type` 오타는 MyBatis가 커넥션 풀을 인식 못 하게 만듦
- 오타 하나로도 DB 연결 실패, `environment is null` 같은 치명적 오류 발생 가능

---

### 📌 요약 포인트

- DTD 선언문에는 `PUBLIC` 키워드가 필수이며, URL은 꺾쇠 없이 따옴표로 감싼다
- XML 속성 오타는 전체 설정이 무시되거나 세션 생성 실패로 이어질 수 있다