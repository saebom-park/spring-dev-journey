# [REVIEW-3-2] 실수노트

> 💻 실습 코드: MemberServiceImpl.java, MemoryMemberRepository.java

---

### 😅 실수 1 — save() 메서드에서 member.setId(sequence) 누락

```java
// 실수 코드
public void save(Member member) {
    sequence++;
    store.put(sequence, member); // ❌ ID가 null 상태로 저장됨
}
```

✅ 정답:

```java
public void save(Member member) {
    sequence++;
    member.setId(sequence); // ✅ ID 할당 필수
    store.put(sequence, member);
}
```

📌 **설명**:

- member 객체의 ID를 저장 전에 반드시 설정해야 함
- 누락되면 Controller에서 반환하는 DTO에 id가 null로 들어감
- ID는 비즈니스 상 중요한 고유 식별자이므로 저장 시점에 반드시 할당 필요

---

### 😅 실수 2 — @Service / @Repository 어노테이션 누락으로 Bean 등록 실패

```java
// 잘못된 구현체 예시 (어노테이션 없음)
public class MemberServiceImpl implements MemberService {
    ...
}

public class MemoryMemberRepository implements MemberRepository {
    ...
}
```

✅ 정답:

```java
@Service
public class MemberServiceImpl implements MemberService {
    ...
}

@Repository
public class MemoryMemberRepository implements MemberRepository {
    ...
}
```

📌 **설명**:

- Spring이 구현체를 자동 등록하려면 @Service, @Repository 같은 어노테이션이 반드시 필요함
- 누락되면 "No qualifying bean" 또는 "NoSuchBeanDefinitionException" 에러 발생
- 특히 인터페이스 기반 DI 구조에서는 구현체가 Bean으로 등록되지 않으면 Controller에서도 주입 실패함

---

### 📌 요약 포인트

- 저장소 구현 시 ID 값 설정을 빼먹지 말자 (`setId()` 필수)
- @Service / @Repository 누락은 **빈 등록 실패**의 핵심 원인
- 자동완성 기능 없이 코딩할 때는 **어노테이션 직접 입력 누락 주의**
- POSTMAN 테스트로 빈 등록 실패를 감지할 수 있음 → 로그 확인 중요

