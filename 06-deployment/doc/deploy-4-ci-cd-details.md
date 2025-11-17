# [DEPLOY-4단계] GitHub Actions CI/CD 해설

> DEPLOY-4의 핵심 흐름을 “왜 이렇게 하는지” 중심으로 설명한 확장 해설 문서입니다.
> 
> 
> CI/CD 전체 구조를 한눈에 이해하는 데 집중합니다.
> 

---

## 🔗 1️⃣ 전체 CI/CD 구조 요약

```
GitHub push
   ↓
GitHub Actions (빌드)
   ↓
JAR 생성
   ↓
EC2에 SSH로 전송
   ↓
systemctl restart springlab23
   ↓
배포 완료
```

> 핵심: 인간이 하던 ‘빌드→전송→재시작’을 GitHub Actions가 대신 수행
> 

---

## 🏗️ 2️⃣ CI(Continuous Integration) 원리

CI는 **코드 변경이 있을 때마다 자동 빌드 & 테스트**를 의미함.

- 버그가 초기에 발견됨
- 팀 개발 시 빌드 충돌 방지
- 항상 “빌드 가능한” 코드 상태 유지

GitHub Actions는 push 이벤트를 잡아서 자동 실행한다.

---

## 🚀 3️⃣ CD(Continuous Deployment) 원리

CD는 “검증된 빌드를 자동 배포하는 과정”.

EC2에 자동 접속해서:

1. JAR 업로드
2. 기존 버전 덮어쓰기
3. Spring 서비스 재시작

까지 모두 포함.

---

## 🧩 4️⃣ 왜 SSH 배포를 쓰는가?

- 가장 단순하고 안정적인 배포 방식
- EC2 → Nginx → Spring 구조에서는 충분히 실무적
- GitHub Actions 마켓플레이스에 검증된 SCP/SSH 액션들이 많음

실무에서는 Docker/ECR/ECS 방식도 있지만

지금 구조에서는 SSH 배포가 가장 빠르고 확실하다.

---

## 🔐 5️⃣ GitHub Secrets 구조

Secrets는 민감 정보 보호용.

| 항목 | 역할 |
| --- | --- |
| EC2_HOST | ssh 접속할 대상 서버 |
| EC2_USER | ubuntu 사용자 |
| EC2_KEY | ssh private key 내용 전체 |

GitHub Actions는 이 값을 안전하게 디코딩해서

EC2에 접속한다.

> 중요한 건 PEM은 파일 업로드가 아니라 텍스트 그대로 복붙해야 한다.
> 

---

## ⚙️ 6️⃣ Deploy Workflow 동작 원리

워크플로우 흐름은 아래 4단계로 이해하면 된다.

### **① 코드 체크아웃**

actions/checkout → 현재 repo 파일을 워크플로우 서버로 가져옴

### **② JDK 설치 후 빌드**

`./gradlew clean build`

→ 로컬에서 하는 빌드 과정 그대로 수행

### **③ 산출물(jar) EC2로 전송**

appleboy/scp-action

→ scp 명령을 자동화한 GitHub Marketplace 액션

### **④ Spring Boot 재시작**

appleboy/ssh-action

→ ssh로 EC2에 명령을 날려서 systemd restart 실행

---

## 🛰️ 7️⃣ CI/CD 실패 시 확인 루틴

| 단계 | 체크포인트 |
| --- | --- |
| Build 실패 | Gradle 오류 / 버전 충돌 |
| Copy 실패 | EC2_HOST, KEY 설정 오류 |
| Permission Denied | PEM 권한 문제, key 형식 오류 |
| Restart 실패 | 서비스 이름 springlab23 오타 / jar 경로 오류 |

---

## 🌱 한 줄 요약

> “로컬 배포는 끝났고, 이제부터는 push = 배포다.”
> 
> 
> GitHub Actions는 봄이의 배포 루틴을 완전히 자동화해주는 실무 필수 기술.
>