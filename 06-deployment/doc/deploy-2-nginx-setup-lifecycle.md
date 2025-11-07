# [DEPLOY-2단계] Nginx 운영 매뉴얼 (deploy-2-nginx-lifecycle)

> EC2 서버에 배포된 Spring Boot 애플리케이션을  
> **Nginx 리버스 프록시(80 → 8080)** 구조로 안정적으로 운영하기 위한  
> 점검 루틴 및 복구 절차를 정리한 매뉴얼입니다.

---

## 🧭 전체 운영 흐름 요약

| 구분 | 항목 | 명령어 | 설명 |
|------|------|--------|------|
| ✅ **1단계** | Spring Boot 서비스 상태 확인 | `sudo systemctl status springlab23 --no-pager` | 앱이 정상적으로 실행 중인지 확인 |
| ✅ **2단계** | Nginx 상태 확인 | `sudo systemctl status nginx --no-pager` | Nginx 프로세스 실행 여부 확인 |
| ✅ **3단계** | Nginx 설정 문법 검사 | `sudo nginx -t` | 설정파일 구문 오류 사전 점검 |
| ✅ **4단계** | Nginx 재시작 | `sudo systemctl restart nginx` | 설정 변경 반영 |
| ✅ **5단계** | 내부 응답 테스트 | `curl http://localhost/hello` | 200 OK 응답 여부 확인 |
| ✅ **6단계** | 외부 접속 테스트 | 브라우저: `http://<EC2_IP>` | 외부 접속 정상 확인 |
| ✅ **7단계** | 로그 점검 | `sudo tail -n 100 /var/log/nginx/error.log`<br>`sudo journalctl -u springlab23 -n 100 --no-pager` | Nginx·SpringBoot 로그 확인 |

---

## ⚙️ 서비스 제어 명령 모음

### 🔹 Nginx 제어

| 명령 | 설명 |
|------|------|
| `sudo systemctl start nginx` | Nginx 시작 |
| `sudo systemctl stop nginx` | Nginx 중지 |
| `sudo systemctl restart nginx` | 설정 반영 후 재시작 |
| `sudo systemctl enable nginx` | 부팅 시 자동 실행 등록 |
| `sudo systemctl disable nginx` | 자동 실행 해제 |

---

### 🔹 Spring Boot 서비스 제어

| 명령 | 설명 |
|------|------|
| `sudo systemctl start springlab23` | Spring Boot 실행 |
| `sudo systemctl stop springlab23` | Spring Boot 중지 |
| `sudo systemctl restart springlab23` | 재시작 |
| `sudo systemctl status springlab23` | 상태 확인 |

---

## 🔍 점검 루틴 (SSH 재접속 시 루틴)

1️⃣ EC2 접속  
```bash
ssh -i <pem키경로> ubuntu@<EC2_IP>
```

2️⃣ Spring Boot / Nginx 실행 상태 확인  
```bash
sudo systemctl status springlab23 --no-pager
sudo systemctl status nginx --no-pager
```

3️⃣ 필요 시 재시작  
```bash
sudo systemctl restart springlab23
sudo systemctl restart nginx
```

4️⃣ 내부 테스트  
```bash
curl http://localhost/hello
```
→ 응답: `Deploy Build Test!` 가 나오면 정상 ✅

5️⃣ 외부 접속 테스트  
브라우저에서 `http://<EC2_IP>` 입력 → 동일 응답 확인

6️⃣ 로그 점검 (문제 발생 시만)  
```bash
sudo tail -n 50 /var/log/nginx/error.log
sudo journalctl -u springlab23 -n 100 --no-pager
```

---

## 💥 자주 발생하는 오류 & 대처

| 증상 | 원인 | 해결 방법 |
|------|------|-----------|
| `502 Bad Gateway` | Spring Boot 비실행 또는 포트 불일치 | `systemctl restart springlab23` 후 재시작 |
| `403 / 404` | 요청 경로 또는 라우팅 오류 | Spring Controller 경로 확인 |
| `nginx -t` 실패 | 설정 오타, 중괄호 `{}` 누락 | `/etc/nginx/sites-available/springlab.conf` 수정 후 재검사 |
| `curl http://localhost` 응답 없음 | Nginx 비활성 상태 | `systemctl restart nginx` |
| 브라우저 접속 불가 | 80포트 방화벽 미허용 | `sudo ufw allow 80/tcp` + `sudo ufw enable` |

---

## 🧾 참고 경로 정리

| 항목 | 경로 |
|------|------|
| Nginx 설정파일 | `/etc/nginx/sites-available/springlab.conf` |
| 활성화 링크 | `/etc/nginx/sites-enabled/springlab.conf` |
| 로그파일 | `/var/log/nginx/access.log` / `/var/log/nginx/error.log` |
| Spring Boot 서비스 단위파일 | `/etc/systemd/system/springlab23.service` |

---

> 📌 **운영 핵심 요약:**  
> “서버가 멈췄을 때는 → 상태 확인 → `nginx -t` 검사 → `restart` → `curl` 테스트 → 로그 확인”  
> 이 5단계 루틴만 몸에 익히면 서버 장애는 대부분 바로 해결 가능하다. 🌿
