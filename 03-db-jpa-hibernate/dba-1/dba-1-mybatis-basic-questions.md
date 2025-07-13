# [DBA-1단계] 질문노트: MyBatis 기본

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: springlab13 실습 기준)
> 

---

### 💡 1. `Book` 클래스의 `toString()`에 왜 `@Override`를 붙여?

| 질문 | 답변 요약 |
| --- | --- |
| `Book` 클래스에서 `toString()` 메서드에 `@Override`가 붙어 있는데, 어디서 상속된 건지 안 보이잖아? | 자바의 모든 클래스는 `java.lang.Object`를 자동으로 상속받아. `Object`에는 기본적으로 `toString()` 메서드가 정의돼 있어서, 우리가 원하는 출력 포맷을 만들기 위해 `@Override`로 재정의한 거야. |

---

### 💡 2. 그럼 `toString()` 말고 내가 직접 `displayInfo()` 같은 메서드를 만들면 안 돼?

| 질문 | 답변 요약 |
| --- | --- |
| 출력용으로 `toString()`을 재정의하지 않고, 내가 원하는 포맷을 리턴하는 `displayInfo()` 메서드를 따로 만들면 되는 거 아냐? | 기술적으로 가능해! 하지만 `System.out.println(book)`처럼 객체를 출력할 때 자바는 자동으로 `toString()`을 호출해. 로그 출력이나 디버깅에서도 `toString()`이 자동 호출되기 때문에, 자바에서는 객체의 문자열 표현을 바꾸려면 `toString()`을 오버라이드하는 게 가장 일반적인 방법이야. |

---

### 💡 3. "MyBatis가 이 파일을 XML 설정으로 인식한다"는 게 무슨 뜻이야?

| 질문 | 답변 요약 |
| --- | --- |
| `mybatis-config.xml` 설명에서 "MyBatis가 이 파일을 XML 설정으로 인식한다"는 말이 나오는데, 그게 무슨 의미야? | MyBatis는 내부적으로 이 XML이 **MyBatis 전용 설정 문서인지** 확인해야 해. 그걸 위해 DTD 선언을 사용해. `<!DOCTYPE configuration ...>` 이 부분이 선언돼 있어야만 MyBatis가 이 파일을 "내가 이해할 수 있는 설정 XML"이라고 판단하고 정상적으로 `SqlSessionFactoryBuilder`가 파싱해서 동작할 수 있어. |

### 💡 4. MyBatis는 설정 파일을 알아서 스캔해서 인식하는 거야?

| 질문 | 답변 요약 |
| --- | --- |
| MyBatis는 설정 파일을 알아서 자동으로 인식하고 Mapper 파일도 스스로 찾아주는 거야? | 아니야! MyBatis는 우리가 `SqlSessionFactoryBuilder`를 통해 명시적으로 지정한 설정 파일(`mybatis-config.xml`)만 스캔해. 또 그 설정 안에 `<mappers>`로 등록된 파일만 인식하지, 자동 탐색은 안 해. DTD는 그저 "이 XML이 MyBatis 전용 설정이야"라고 알려주는 역할만 할 뿐이야. |

---

### 💡 5. InputStream은 뭐야?

| 질문 | 답변 요약 |
| --- | --- |
| `InputStream inputStream = Resources.getResourceAsStream(...)` 이렇게 쓰는데, InputStream이 정확히 뭐야? | `InputStream`은 자바에서 어떤 데이터(파일, 네트워크 등)를 "바이트 단위로 읽어오는 통로"야. 설정파일이나 리소스를 읽을 때 자주 사용돼. 우리 실습에서는 `mybatis-config.xml`을 읽기 위해 사용됐고, 이걸 `SqlSessionFactoryBuilder`에 넘겨서 설정을 적용한 거야. |

---

### 💡 6. 우리는 try-with-resources로 자동 닫기 쓰는데, POOLED는 왜 또 필요한 거야?

| 질문 | 답변 요약 |
| --- | --- |
| JDBC에서 `try-with-resources`로 자동으로 연결 닫아주잖아. 그런데 MyBatis에서 `POOLED` 커넥션 풀 방식은 왜 또 필요한 거야? | `try-with-resources`는 "닫는 타이밍"을 자동화해주는 문법이고, `POOLED`는 "실제로 연결을 끊지 않고 재사용하는 전략"이야. MyBatis에서는 `session.close()`가 호출돼도 실제 연결은 커넥션 풀로 반납되기 때문에 다음 요청에서 재사용돼. 즉, 둘은 충돌하지 않고 함께 쓰이며, `POOLED`는 성능을 위한 최적화 전략이야. |

---

### 💡 7. config.xml이랑 Mapper.xml의 DTD 선언문이 비슷한데 뭐가 달라?

| 질문 | 답변 요약 |
| --- | --- |
| `mybatis-config.xml`과 `BookMapper.xml`의 XML 맨 위 DTD 선언문을 보면 거의 똑같고 `configuration` vs `mapper`만 달라 보여. 이게 무슨 차이야? | 정확히 봤어! 두 문서는 모두 MyBatis 전용 XML이지만, 용도가 달라서 각각의 DTD가 존재해. 설정 파일은 `<configuration>` 루트 태그로 DB 설정, 매퍼 등록 등을 하고, 매퍼 파일은 `<mapper>` 루트 태그로 SQL 쿼리 정의 및 인터페이스 매핑을 담당해. 그래서 DTD도 각각 "Config 3.0" vs "Mapper 3.0"으로 다르고, 태그 구조도 달라. |

---

### 💡 8. JDBC 드라이버는 뭐야? driver에 적는 클래스 이름은 왜 필요해?

| 질문 | 답변 요약 |
| --- | --- |
| `<property name="driver" value="com.mysql.cj.jdbc.Driver"/>`에서 드라이버는 무슨 의미야? 예전에 H2 쓸 때는 `jdbc:h2:mem:testdb` 같은 것도 썼던 것 같은데... | JDBC 드라이버는 자바 프로그램과 DB 사이에서 "통역사" 역할을 해주는 클래스야. DB마다 드라이버가 다르고, 자바가 JDBC API를 통해 DB와 통신할 수 있도록 연결해줘. MySQL은 `com.mysql.cj.jdbc.Driver`, H2는 `org.h2.Driver`처럼 DB 종류마다 다른 드라이버 클래스를 지정해야 해. |

---

### 🌱 정리 키워드

- 모든 클래스는 `Object`를 자동 상속하고, `toString()`을 가지고 있다
- `@Override`는 해당 메서드가 상속 메서드임을 명확히 하고 문법 검증도 가능하게 해준다
- 출력 포맷을 직접 만들고 싶으면 별도 메서드를 만들어도 되지만, 자바의 기본 출력 흐름은 `toString()`이다
- **MyBatis는 `mybatis-config.xml` 파일의 DTD 선언을 통해 이 XML을 MyBatis 전용 설정 파일로 인식한다**
- **MyBatis는 설정파일과 매퍼 파일을 자동으로 스캔하지 않고, 우리가 지정한 것만 인식한다**
- **`InputStream`은 설정파일이나 리소스를 바이트 단위로 읽는 입력 스트림이다**
- `try-with-resources`는 자바의 자동 닫기 문법이고, `POOLED`는 실제로 닫지 않고 커넥션 풀에 반납하는 전략이다
- 설정파일과 매퍼파일은 DTD 선언문은 비슷하지만, **용도와 루트 태그 이름이 다르다** (`configuration` vs `mapper`)