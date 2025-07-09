# [SPRING-4단계] 질문노트: 요청 매핑

> 💬 봄이가 spring-4 실습 중 궁금했던 것들 + 온이의 설명 요약
> 
> 
> (코드: springlab9 ~ springlab10 실습 기준)
> 

---

### 💡 1. UserDto에서 getter 메서드를 왜 선언해야 해?

| 질문 | 답변 요약 |
| --- | --- |
| UserDto 클래스에서 getId(), getName() 같은 getter들을 선언했는데, 실제 코드에서 호출한 적이 없어. 그럼 왜 꼭 만들어야 해? | Spring Boot는 객체를 JSON으로 변환할 때 Jackson이라는 라이브러리를 사용하는데, 이 Jackson은 필드 값을 가져오기 위해 반드시 getter 메서드를 사용해. 우리가 직접 호출하지 않아도, Jackson이 내부적으로 getId() 같은 메서드를 자동 호출해서 JSON 응답을 만들어줘! |

---

### 🌱 정리 키워드

- Jackson 직렬화: getter를 통해 JSON 응답 생성
- Spring Boot 응답 처리: 객체 리턴 → 자동 JSON 변환
- getter 미작성 시 JSON 필드 누락 또는 오류 발생