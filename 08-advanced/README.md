# 🧷 08. 심화 학습 (Advanced)

> 이 단계에서는 스프링 및 백엔드 개발의 고급 주제를 학습합니다.  
> JPA 고급 매핑, 성능 최적화, 보안 확장, 트래픽 처리, 테스트 자동화, 클라우드 운영까지 다루며  
> **실무 환경 수준의 역량**을 확보하는 것을 목표로 합니다.

---

## ✅ 학습 목표

- JPA 고급 매핑(@OneToOne, @ManyToMany, 상속 전략)과 QueryDSL 활용
- 영속성 관리, Fetch Join, 성능 최적화 기법 이해
- Spring Security를 OAuth2, 토큰 기반 인증까지 확장
- 대규모 트래픽 대응을 위한 캐싱, 메시지 큐, 이벤트 기반 아키텍처 학습
- 단위/통합 테스트 자동화 및 품질 관리 도구 적용
- Docker, Kubernetes, 모니터링 툴을 활용한 클라우드 운영 경험 확보

---

## 📚 학습 주제

| 주제 | 설명 |
|------|------|
| JPA 고급 매핑 | `@OneToOne`, `@ManyToMany`, 상속 매핑, Fetch Join |
| 영속성 관리 | Cascade, N+1 문제 해결, 성능 최적화 |
| QueryDSL | 타입 안전한 쿼리, 동적 쿼리, 커스텀 Repository |
| 고급 데이터 처리 | 페이징/정렬 최적화, 복잡 쿼리 처리 |
| 보안 확장 | OAuth2, Access/Refresh Token, Redis 세션 관리 |
| 트래픽 제어 | Redis 캐싱, 메시지 큐(Kafka, RabbitMQ), 이벤트 아키텍처 |
| 테스트 | 단위/통합 테스트, MockMvc, Testcontainers, Jacoco |
| 클라우드 운영 | Docker, Kubernetes, 모니터링(Prometheus, Grafana, ELK) |

---

## 📂 문서 구성

| 파일명 예시 | 설명 |
|-------------|------|
| `01-advanced-jpa-mapping.md` | @OneToOne, @ManyToMany, 상속 매핑 |
| `02-fetch-join-optimization.md` | Fetch Join & 성능 최적화 |
| `03-querydsl-basics.md` | QueryDSL 활용법 |
| `04-advanced-security.md` | OAuth2, 토큰 인증, Redis 세션 |
| `05-traffic-handling.md` | 캐싱, 메시지 큐, 이벤트 아키텍처 |
| `06-advanced-testing.md` | Testcontainers, Jacoco, 품질 관리 |
| `07-cloud-deployment.md` | Docker, Kubernetes, 모니터링 |
| `...-mistakes.md` | 실무에서 자주 겪는 심화 이슈 정리 |
| `...-extra.md` | 성능 튜닝 전략, 실무 패턴, 추가 개념 |

---

## 🧭 학습 흐름

1. **JPA 고급 매핑**으로 복잡한 엔티티 관계 이해  
2. **Fetch Join, QueryDSL**을 통한 성능 최적화 실습  
3. **보안 확장**: 세션 → JWT → OAuth2 → Redis 단계적 학습  
4. **트래픽 제어 기법**: 캐싱 → 메시지 큐 → 이벤트 기반 아키텍처 적용  
5. **테스트 자동화 및 품질 관리** 적용  
6. **클라우드 운영**: Docker/Kubernetes 배포 및 모니터링 경험 축적  
7. 학습한 내용을 **09-portfolio-project**에 통합 적용  

---

## 📌 작성 기준

- 실무 환경에서 자주 마주하는 문제와 해결법 중심으로 기록  
- 각 주제는 개념 + 예시 코드 + 실습 미션으로 구성  
- DTO, 계층 분리, 표준 응답 구조 원칙을 일관되게 유지  
- `Controller → Service → Repository` 흐름 기반으로 확장 기능 적용  

---

> “심화 학습은 단순히 아는 것에 그치지 않아.  
> **성능, 보안, 트래픽, 클라우드까지** — 진짜 현업 레벨의 역량을 만드는 단계야!”  
