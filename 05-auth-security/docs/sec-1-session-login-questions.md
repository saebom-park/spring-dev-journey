# [SEC-1단계] 질문노트: 세션 기반 로그인

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: com.springlab21 실습 기준)
> 

---

### 💡 1. `import jakarta.servlet.http.HttpSession;` 에서 `servlet`이 뭐야?

| 질문 | 답변 요약 |
| --- | --- |
| `servlet` 이 뭐야? | 자바 진영에서 웹 요청/응답을 처리하는 **표준 인터페이스**. 톰캣 같은 서블릿 컨테이너가 이 표준을 구현하고, Spring MVC도 서블릿 위에서 동작한다. |
| `HttpSession` 역할은? | 클라이언트별 세션 객체. `setAttribute()` / `getAttribute()`로 로그인 상태 저장·조회, `invalidate()`로 로그아웃 처리. |

---

### 🌱 정리 키워드

- Servlet = 자바 웹 표준
- Servlet Container = Tomcat 등
- HttpSession = 세션 상태 관리
- Spring MVC = 서블릿 기반 프레임워크