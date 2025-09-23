# [SEC-3-EXTRA2단계] 질문노트: DB 연동 UserDetailsService (com.springlab21.security)

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: com.springlab21.security 실습 기준)
> 

---

### 💡 1. Collection이랑 Collections 차이는 뭐야?

| 질문 | 답변 요약 |
| --- | --- |
| Collection이랑 Collections 차이가 뭐야? | Collection은 인터페이스라서 List, Set 같은 자료구조의 부모 느낌이고, Collections는 유틸리티 클래스라서 `sort()`나 `singletonList()` 같은 도우미 메서드를 모아둔 거야. 즉, 하나는 타입이고 하나는 도구상자 같은 거지. |

🌱 정리 키워드

- `Collection` = 인터페이스
- `Collections` = 유틸리티 클래스

---

### 💡 2. CustomUserDetails에서 Repository까지 써서 DB 조회하면 안 되는 거 아냐?

| 질문 | 답변 요약 |
| --- | --- |
| CustomUserDetails에서 바로 Repository 주입받아서 DB 조회하면 안 돼? | 안 돼. CustomUserDetails는 그냥 User 엔티티를 감싸주는 껍데기고, DB 조회는 UserDetailsService가 맡아야 해. 스프링 시큐리티도 인증할 때 무조건 UserDetailsService를 호출하도록 만들어져 있어서, 두 역할을 섞으면 동작이 꼬여. |

🌱 정리 키워드

- `CustomUserDetails` = 변환 (Adapter)
- `CustomUserDetailsService` = 조회 (Loader)