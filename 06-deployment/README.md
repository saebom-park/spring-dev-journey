# 🧷 06. 배포 (Deployment)

> 이 단계에서는 로컬에서 완성한 Spring Boot 프로젝트를  
> **AWS EC2에 배포하고, 자동 실행 → Nginx → HTTPS → CI/CD** 순으로  
> 실무 수준의 배포 환경을 구축합니다.

---

## ✅ 학습 목표

- 로컬 프로젝트를 JAR로 빌드하고 EC2 서버에 직접 배포  
- systemd를 통한 자동 실행 환경 구성  
- Nginx 리버스 프록시 설정 및 HTTPS(SSL) 적용  
- GitHub Actions 기반 CI/CD 자동 배포 파이프라인 구축  

---

## 📚 학습 주제

| 주제 | 설명 |
|------|------|
| EC2 수동 배포 | JAR 빌드 → 서버 전송 → systemd 서비스 등록 |
| 리버스 프록시 | Nginx로 80 → 8080 트래픽 전달 |
| HTTPS 인증 | Certbot으로 SSL 인증서 발급 및 443 리다이렉트 |
| CI/CD 자동화 | GitHub Actions로 자동 빌드·배포 구성 |

---

## 📂 문서 구성

| 파일명 | 설명 |
|---------|------|
| `deploy-1-ec2.md` | EC2 서버 생성, JAR 업로드, systemd 서비스 등록 |
| `deploy-2-nginx-setup.md` | Nginx 설치, 리버스 프록시 설정 (80 포트 연결) |
| `deploy-3-https-ssl.md` | Certbot 기반 SSL 인증, HTTPS 적용 |
| `deploy-4-ci-cd.md` | GitHub Actions를 활용한 자동 배포 파이프라인 구성 |
| `...-questions.md` | 각 단계별 질문노트 |
| `...-mistakes.md` | 각 단계별 실수노트 |
| `...-lifecycle.md` | 운영/재시작/중지 매뉴얼 정리 |

---

## 🧭 학습 흐름

1. **DEPLOY-1:** EC2 서버에 직접 배포 (수동 실행 → systemd 자동화)  
2. **DEPLOY-2:** Nginx 리버스 프록시 설정 (80 → 8080 트래픽 전달)  
3. **DEPLOY-3:** HTTPS 적용 (SSL 인증서 + 80→443 리다이렉트)  
4. **DEPLOY-4:** GitHub Actions로 자동 배포 (CI/CD 파이프라인 완성)

---

## 📌 작성 기준

- AWS EC2 (Ubuntu 22.04) 기준  
- 서비스 자동 실행은 **systemd**, 웹 서버는 **Nginx** 사용  
- HTTPS 인증은 **Let’s Encrypt (Certbot)** 기반  
- CI/CD는 **GitHub Actions + EC2 SSH 배포** 방식  
- README는 “한눈에 단계별 구조가 보이도록” 구성  

---

> “배포는 단순한 전달이 아니라,  
> **서비스의 생명 주기를 설계하는 기술이다.**” 🚀  
