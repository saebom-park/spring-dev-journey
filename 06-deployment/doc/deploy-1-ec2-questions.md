# [DEPLOY-1단계] 질문노트: EC2 서버 생성 & JAR 수동 배포 (ec2)

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: com.springlab23 실습 기준)
> 

---

### 💡 1. yml 파일이 왜 여러개야?

| 질문 | 답변 요약 |
| --- | --- |
| yml 파일이 왜 여러 개야? | 환경(개발·운영 등)에 따라 설정을 분리하기 위해서야. Spring Boot는 `application.yml`을 먼저 읽고, 그 다음 `spring.profiles.active`로 지정한 환경(`application-dev.yml`, `application-prod.yml`)을 병합해서 적용해. |
| 파일별 역할은 어떻게 달라? | `application.yml`은 공통 설정, `application-dev.yml`은 개발용(DB 자동 생성, show_sql=true), `application-prod.yml`은 운영용(DB 덮어쓰기 방지, show_sql=false)으로 사용돼. |
| 실행할 때 어떤 파일을 사용하라고 지정해? | 실행 명령어에 `--spring.profiles.active=dev` 또는 `prod`를 붙이면, 해당 프로파일이 활성화돼. 예: `java -jar build/libs/deploy-lab-1.0.0.jar --spring.profiles.active=dev` |
| 왜 이렇게 나누는 게 중요해? | 환경별 DB나 로그 설정을 분리해두면 실수로 운영 DB를 건드리는 사고를 예방할 수 있고, 배포 자동화 시에도 안전하게 관리 가능해. |

---

### 💡 2. application.yml에 active: dev는 왜 기본으로 설정해?

| 질문 | 답변 요약 |
| --- | --- |
| 공통 설정 파일인데 왜 dev로 지정해둔 거야? | 아무 프로필도 지정하지 않으면 Spring Boot는 `application.yml`만 읽고 끝나서, 실행 시 추가 설정(`application-dev.yml`)을 병합하지 않아. 그래서 로컬 개발 환경을 기본으로 쓰기 위해 `active: dev`를 기본값으로 둔 거야. |
| 그러면 운영 환경에서는 어떻게 전환해? | 실행할 때 명령어에 `--spring.profiles.active=prod`를 붙이면 `application-prod.yml`이 병합돼. 즉, 로컬은 dev, 서버는 prod로 구분해 실행하는 구조야. |
| 결국 active: dev의 역할은 뭐야? | “기본 실행 시 개발환경(dev) 설정을 함께 읽게 하라”는 의미야. 이 덕분에 IntelliJ에서 바로 실행해도 dev 설정이 자동 적용돼. |

---

### 💡 3. logging.level.root: info는 무슨 뜻이야?

| 질문 | 답변 요약 |
| --- | --- |
| `logging.level.root: info`는 무슨 역할이야? | 애플리케이션 전체 로그의 기본 레벨을 설정하는 거야. `root`는 모든 로그의 최상위 로거를 뜻하고, `info`는 INFO 이상(INFO·WARN·ERROR)만 출력하도록 제한해. |
| 왜 info로 설정하는 게 좋아? | DEBUG는 개발용으로 로그가 너무 많고, 운영에서는 성능과 보안상 적절한 양만 보여주는 INFO 수준이 안정적이야. |
| 환경별로 다르게 쓸 수 있어? | 가능해! 보통 `application-dev.yml`엔 debug, `application-prod.yml`엔 info로 둬서 개발은 상세하게, 운영은 간결하게 관리해. |

---

### 💡 4. application-prod.yml에도 공통 내용을 왜 또 써야 해?

| 질문 | 답변 요약 |
| --- | --- |
| 공통 yml에도 있는데 prod에 또 써야 해? | `application-prod.yml`은 공통 설정을 **덮어쓰거나 환경별로 조정**할 수 있는 공간이야. 지금은 내용이 같아 보여도, 실제 운영에서는 포트, 로그 레벨, DB, SSL 등 설정을 바꾸기 때문에 구조적으로 분리해둬야 해. |
| 실제로는 어떻게 병합돼? | Spring Boot는 `application.yml`을 먼저 읽고, `--spring.profiles.active=prod`가 지정되면 `application-prod.yml`을 병합해. 같은 키가 있으면 prod 값이 우선 적용돼. |
| 왜 미리 만들어두는 게 좋아? | CI/CD나 클라우드 배포 시 `application-prod.yml`이 기본 탐색 대상이기 때문이야. 환경별 설정 분리를 표준화하면 운영 중 실수(예: dev DB 접속)도 막을 수 있어. |

---

### 🌱 정리 키워드

- 환경 분리 (Environment Separation)
- `spring.profiles.active`
- `application.yml` 공통 설정
- `application-dev.yml` 개발용
- `application-prod.yml` 운영용
- 설정 병합 및 우선순위
- 기본 프로필(dev) 지정 이유
- 로그 레벨(logging level)
- `root: info` = INFO 이상만 출력
- dev=debug / prod=info 구조
- 운영용 prod 파일 = 공통값 덮어쓰기용