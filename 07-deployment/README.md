# 07-deployment
Spring Boot 프로젝트를 AWS EC2 환경에 배포하며,  
수동 배포 → 자동 실행 → Nginx → HTTPS → CI/CD까지 실무형 배포 구조를 구축하는 단계입니다.

---

## 🗂️ 구성 안내
- `docs/` : EC2 배포, Nginx, HTTPS, CI/CD 등 단계별 배포 문서가 포함됩니다.
- 배포 단계는 운영 환경과 직접 연결되므로, 필요한 경우  
  **details / lifecycle / mistakes / questions 문서를 함께 사용합니다.**

---

## 🎯 학습 목표

- Spring Boot 프로젝트를 JAR로 빌드하고 EC2 서버로 배포
- systemd 기반 서비스 자동 실행 구성
- Nginx 리버스 프록시 설정을 통한 외부 트래픽 연결
- Let’s Encrypt(Certbot) 기반 HTTPS 적용
- GitHub Actions로 CI/CD 자동 배포 구성

---

## 🧠 학습 구성 (총 4단계)

| 단계       | 주제        | 설명                                |
|----------|-----------|-----------------------------------|
| DEPLOY-1 | EC2 수동 배포 | JAR 빌드 → 서버 전송 → systemd 서비스 등록   |
| DEPLOY-2 | 리버스 프록시   | Nginx 80 → 8080 연결, 서버 운영 구성      |
| DEPLOY-3 | HTTPS 적용  | Certbot SSL 인증서 발급 및 80→443 리다이렉트 |
| DEPLOY-4 | CI/CD 자동화 | GitHub Actions 기반 자동 빌드·배포 파이프라인  |

---

## 📂 문서 구성 규칙

배포 단계는 설정·운영·오류 복구 요소가 많기 때문에  
**각 단계별 문서 세트는 다음과 같이 구성될 수 있습니다.**

| 파일명 형식                          | 설명                            |
|---------------------------------|-------------------------------|
| `deploy-<단계>-<주제>.md`           | 기본 수업자료(핵심 개념 + 설정 + 실습 흐름)   |
| `deploy-<단계>-<주제>-details.md`   | 개념 확장·원리 설명 (필요 시 생성)         |
| `deploy-<단계>-<주제>-lifecycle.md` | 운영/점검/재시작 매뉴얼 (운영 관련 시 생성)    |
| `deploy-<단계>-<주제>-mistakes.md`  | 실습 중 발생한 오류 및 해결 과정 (필요 시 생성) |
| `deploy-<단계>-<주제>-questions.md` | 질문 정리 및 핵심 해설 (필요 시 생성)       |

> Nginx 설정은 운영·확장과 직접 연결되므로  
> **details, lifecycle 문서가 함께 존재할 수 있습니다.**

---

## 🧭 학습 흐름

1. **DEPLOY-1 — EC2 수동 배포**
    - JAR 빌드 후 EC2 서버로 전송
    - systemd 서비스 등록 및 자동 실행 구성

2. **DEPLOY-2 — Nginx 리버스 프록시 구성**
    - 외부 80 → 내부 8080 트래픽 전달
    - 서버 운영 절차(재시작, 로그 확인 등) 점검
    - 설정 파일 구조와 동작 원리 이해

3. **DEPLOY-3 — HTTPS 적용**
    - Certbot으로 SSL 인증서 발급
    - HTTP → HTTPS 리다이렉트 구성
    - 인증서 자동 갱신 환경 설정

4. **DEPLOY-4 — CI/CD 자동 배포**
    - GitHub Actions에서 자동 빌드
    - SSH 기반 EC2 배포 자동화
    - Secrets 구성 및 배포 실패 대응

---

## 📌 작성 기준

- AWS EC2 (Ubuntu 22.04) 환경 기준
- 서비스 자동 실행: **systemd**
- 리버스 프록시 및 정적 자원 처리: **Nginx**
- HTTPS 인증: **Let’s Encrypt (Certbot)**
- 자동 배포: **GitHub Actions + SSH 방식**
- 문서 구성은 각 단계의 실행 순서가 명확하게 보이도록 작성