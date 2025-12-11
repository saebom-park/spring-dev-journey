# DEPLOY 서비스 운영 라이프사이클 (deploy-lifecycle-overall)

> “배포는 끝이 아니라 시작이다.”
> 
> 
> 이 문서는 DEPLOY-1 ~ DEPLOY-4로 구축한 환경을
> 
> **매일 안정적으로 운영·점검·복구하는 전체 흐름**을 정리한 실무 운영 매뉴얼입니다.
> 

---

## 💡 핵심 개념 요약

| 축 | 대상 | 핵심 역할 | 대표 명령/행동 |
| --- | --- | --- | --- |
| 서버 | EC2 (Ubuntu 22.04) | SSH 접속, 자원/상태 점검 | `ssh`, `top`, `df -h` |
| 앱 | Spring Boot + systemd | 서비스 실행/재시작/상태 확인 | `systemctl status springlab23` |
| 프록시 | Nginx | 80/443 수신, 리버스 프록시 | `nginx -t`, `systemctl restart nginx` |
| 보안 | HTTPS / SSL | 인증서 관리 및 암호화 | `certbot renew --dry-run` |
| 배포 | GitHub Actions | 자동 빌드, 자동 배포 | Actions 탭 |
| 로그 | 앱 / 서버 / Nginx | 장애 원인 추적 | `journalctl`, `/var/log/nginx/*` |

---

## 🧾 전체 운영 플로우

### 1) 전체 인프라 구조

```
사용자 브라우저
   ↓ (80/443)
[Nginx (EC2)]
   ↓ (proxy_pass http://localhost:8080)
[Spring Boot (8080)]
   ↓
DB 등 백엔드 리소스

코드 변경
   ↓ push(main)
[GitHub Actions]
   ↓ SSH
[EC2 /opt/app/springlab23/ + systemd restart]

```

**핵심 정보**

- EC2 유저: **ubuntu**
- Spring Boot 서비스명: **springlab23.service**
- JAR 위치: `/opt/app/springlab23/`
- Nginx 설정: `/etc/nginx/sites-available/springlab.conf`
- HTTPS 인증서: `/etc/letsencrypt/live/도메인/`

---

### 2) EC2 접속 & 기본 점검 루틴

### ✔ EC2 접속

```
ssh -i springlab-key.pem ubuntu@44.223.10.161

```

### ✔ 자원 점검

```
top
df -h
ps aux | grep springlab23

```

---

### 3) Spring Boot (systemd) 운영

### ✔ 상태 확인

```
sudo systemctl status springlab23

```

### ✔ 실행 제어

```
sudo systemctl restart springlab23
sudo systemctl stop springlab23
sudo systemctl start springlab23

```

### ✔ 로그 확인

```
journalctl -u springlab23 -n 100
journalctl -u springlab23 -f
journalctl -u springlab23 --since "today"

```

---

### 4) Nginx 운영

### ✔ 설정 파일

```
sudo nano /etc/nginx/sites-available/springlab.conf

```

문법 체크 → 재시작:

```
sudo nginx -t
sudo systemctl restart nginx

```

### ✔ 로그

```
sudo tail -n 100 /var/log/nginx/access.log
sudo tail -n 100 /var/log/nginx/error.log

```

---

### 5) HTTPS / SSL (Certbot)

### ✔ 인증서 확인

```
sudo ls -l /etc/letsencrypt/live/44.223.10.161.nip.io

```

### ✔ 갱신(dry-run)

```
sudo systemctl stop nginx
sudo certbot renew --dry-run
sudo systemctl start nginx

```

---

### 6) GitHub Actions 자동 배포 운영

### ✔ 배포 플로우

1. 코드 push(main)
2. GitHub Actions → build
3. EC2로 JAR 자동 업로드
4. 서비스 자동 재시작

### ✔ 배포 후 확인

EC2:

```
sudo systemctl status springlab23

```

브라우저:

```
https://44.223.10.161.nip.io/hello

```

### ✔ 배포 실패 유형 & 원인

| 증상 | 원인 | 확인 위치 |
| --- | --- | --- |
| build 실패 | 테스트/문법 오류 | Gradle build step |
| scp 실패 | 경로/권한 문제 | EC2 권한, 소유자 |
| permission denied | pem 문제 | Secrets, 권한 |
| restart 실패 | 서비스명 오류 | systemctl 로그 |

---

### 7) 장애 대응 체크리스트

### 🟥 HTTPS 접속 불가

- nginx 상태
- spring 상태
- 80/443 포트
- error.log 확인

### 🟥 502/504/500

- spring 서비스 다운
- upstream 오류
- 재시작 + 로그 분석

### 🟥 새 코드 반영 안 됨

- deploy workflow 성공 여부
- JAR 최신 여부
- systemd restart 여부

### 🟥 EC2 재부팅 후 서비스 비활성

- `systemctl status springlab23`
- `systemctl start springlab23`

---

### 8) 리소스 점검 루틴

### ✔ CPU/메모리

```
top

```

### ✔ 디스크 용량

```
df -h

```

### ✔ 포트 상태

```
sudo lsof -i -P -n | grep LISTEN

```

---

## 📌 요약

- **systemd 재시작**: 운영의 핵심
- **nginx -t → restart nginx**: 프록시의 기본 루틴
- **certbot 갱신은 반드시 nginx stop 필요**
- **CI/CD 로그 읽기** = 실무 역량
- 장애 → “어느 층에서 깨졌는가?”를 먼저 판단

---

## 🧪 실습 미션

### 1) 운영 루틴 시뮬레이션

- EC2 접속
- top / df -h 확인
- systemctl 상태 확인
- nginx/error.log 읽어보기

### 2) 자동 배포 연습

- 코드 주석 수정 → push → 자동 배포
- /hello 정상 응답 확인

### 3) SSL 갱신 dry-run

- nginx stop
- certbot renew --dry-run
- nginx start

### 4) 장애 연습

- springlab23 중지
- /hello 에러 확인
- 로그 분석 후 재시작