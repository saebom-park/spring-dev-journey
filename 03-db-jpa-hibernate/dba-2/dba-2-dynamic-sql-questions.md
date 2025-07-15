# [DBA-2단계] 질문노트: 동적 SQL & resultMap

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: springlab14 실습 기준)
> 

---

### 💡 1. 왜 `<id>`는 따로 쓰고 나머지는 `<result>`로 쓰는 거야?

| 질문 | 답변 요약 |
| --- | --- |
| ProductMapper.xml에서 `<id property="id" column="product_id"/>` 로 쓰고, 나머지는 `<result>`로 쓰는 이유는 뭐야? | `<id>`는 기본키(PK)를 의미하고, MyBatis가 객체 식별/중복 제거/연관관계 매핑 시 이걸 기준으로 판단해. 반면 나머지 일반 컬럼은 `<result>`로 구분해서 쓴 거야. 전부 `<result>`만 써도 동작은 하지만, 식별자 명시는 best practice야. |

---

### 💡 2. `<resultMap>`의 id는 아무 이름이나 써도 되는 거야?

| 질문 | 답변 요약 |
| --- | --- |
| `<resultMap id="productResultMap" ...>`에서 `id`는 아무 이름이나 붙여도 되는 거야? | 응, `resultMap`의 `id`는 개발자가 자유롭게 명명할 수 있어. 다만, 이 `id`는 `resultMap="..."`에서 참조되기 때문에 **프로젝트 내에서 유일해야 하고 의미를 잘 드러내는 이름**으로 짓는 게 좋아. |

---

### 💡 3. `<where>` 안에 있는 `test` 속성이 뭐야? 그리고 `&gt;`는 왜 쓰는 거야?

| 질문 | 답변 요약 |
| --- | --- |
| `<if test="price != null">`에서 `test` 속성은 뭔 뜻이야? `&gt;=`는 또 왜 저렇게 써? | `test`는 자바 조건식처럼 동작해서, 해당 조건이 true일 때만 내부 SQL을 출력해줘. `&gt;`는 XML에서 `>` 기호를 그대로 쓰면 파싱 오류가 나기 때문에 HTML 특수문자로 쓰는 거야 (`&gt;` = `>`). |

---

### 💡 4. `Map<String, Object>` 관련 개념이 잘 이해가 안 돼

| 질문 | 답변 요약 |
| --- | --- |
| ProductMapper.java에서 `findByCondition(Map<String, Object> param)` 이렇게 쓰는 부분이 있는데, 이게 무슨 의미야? | 이건 조건을 담을 수 있는 **동적 파라미터 컨테이너**야. key는 파라미터 이름 (`name`, `price` 등), value는 그에 대응하는 실제 값이 들어가. XML에서는 `#{name}`, `#{price}`처럼 이 Map의 key를 기준으로 값을 꺼내서 SQL에 바인딩해줘. |
| `Map<String, Object>`에서 굳이 `String`이랑 `Object`를 쓰는 이유는 뭐야? | `Map`은 key와 value의 타입을 명시하는데, 여기서 `String`은 파라미터 이름 (`name`, `price`)을 뜻하고, `Object`는 그 값이 될 수 있는 아무 타입(int, String 등 모든 값)을 허용해. 조건 값의 타입이 다양하니까 value는 범용적인 `Object`로 선언하는 거야. |
| `<String, Object> param`은 웬만하면 저렇게 고정해서 쓰는 거야? | 응, MyBatis에서 다중 조건 검색을 처리할 땐 `Map<String, Object>`를 거의 관용적으로 사용해. key는 SQL에서 참조할 이름이고, value는 다양한 타입이 들어올 수 있어서 이 조합이 가장 유연하고 안전해. |

---

### 💡 5. 별명(alias)을 등록했는데도 전체 클래스명을 쓰는 건 왜 그런 거야?

| 질문 | 답변 요약 |
| --- | --- |
| `<typeAlias alias="Book" type="com.springlab13.Book"/>`처럼 별명을 등록했는데도, `resultType="com.springlab13.Book"`처럼 전체 경로를 쓴 이유가 뭐야? | 실제로는 `resultType="Book"`만 써도 되는데, 수업자료에서는 전체 클래스명을 쓴 건 단순한 실수이거나 명시적으로 보여주려는 의도였을 수 있어. MyBatis에서는 alias를 등록하면 짧은 이름만 써도 문제없이 작동해. |

---

### 💡 6. `isEmpty()` vs `isBlank()` 차이가 뭐야?

| 질문 | 답변 요약 |
| --- | --- |
| `isEmpty()`랑 `isBlank()`는 뭐가 달라? 언제 어떤 걸 써야 해? | `isEmpty()`는 길이가 0인 문자열만 true야. 즉, `""`만 해당됨. `isBlank()`는 공백 문자(스페이스, 탭 등)만 있어도 true를 반환함. 즉, `"   "`도 빈 문자열로 간주해줌. 사용자 입력 검증엔 `isBlank()`가 더 안전하고 유용해! |

---

---

### 🌱 정리 키워드

- `<id>`는 기본키 매핑용 태그
- MyBatis 내부 캐시/중복 제거에 사용됨
- `<result>`는 일반 컬럼용 태그
- `resultMap`에서는 이 둘을 구분해주는 것이 안전함
- `<resultMap>`의 `id`는 개발자 지정
- `id`는 유일해야 하며 resultMap="..."에서 참조됨
- 의미 있는 이름으로 짓는 것이 유지보수에 유리함
- `<if>`의 `test`는 조건식으로, true일 때 해당 SQL 조각이 실행됨
- XML 안에서는 `>`를 `&gt;`, `<`는 `&lt;`로 써야 파싱 오류 방지됨
- `Map<String, Object> param`은 조건들을 담기 위한 동적 파라미터 컨테이너
- XML의 `#{}`는 이 Map의 key를 참조해 값 바인딩함
- `Map<String, Object>`에서 key는 파라미터 이름, value는 다양한 타입(int, String 등 포함)을 담을 수 있음
- 다양한 타입을 받기 위해 value는 `Object`로 선언함
- `Map<String, Object>`는 MyBatis에서 조건 검색 시 자주 사용하는 관용적 타입
- `typeAlias`를 등록하면 XML에서 클래스명을 짧게 쓸 수 있음
- alias가 등록되어 있으면 `Book`처럼 간단히 써도 되고, 전체 경로도 여전히 유효함
- 수업자료에서 전체 경로를 쓴 건 실수이거나 의도적인 명시였을 가능성이 있음
- `isEmpty()`는 문자열이 완전히 비었을 때만 true (`""`)
- `isBlank()`는 공백 문자만 있어도 true (`" "` 포함)
- 사용자 입력 검증엔 `isBlank()`가 더 유리함