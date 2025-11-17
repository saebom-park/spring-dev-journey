# [DEPLOY-3단계] 실수노트

> 💻 실습 환경: EC2 (Ubuntu 22.04) + Certbot + Nginx + HTTPS
> 
> 
> 💡 주제: SSL 인증서 발급 및 HTTPS 설정
> 

---

### 😅 실수 1 — Certbot 패키지명 오타

```bash
sudo apt install cerbot python3-cerbot-nginx -y
```

✅ **정답:**

```bash
sudo apt install certbot python3-certbot-nginx -y
```

📌 **설명:**

`cerbot`, `cerbot-nginx` 모두 잘못된 패키지명이라

APT가 패키지를 찾지 못하고 설치가 실패했다.

Certbot은 반드시 정확한 철자로 설치해야 한다.

---

### 😅 실수 2 — Nginx를 끄지 않은 상태로 인증 시도

```bash
sudo certbot certonly --standalone -d 44.223.10.161.nip.io
# → Could not bind TCP port 80
```

✅ **정답:**

```bash
sudo systemctl stop nginx
sudo certbot certonly --standalone -d 44.223.10.161.nip.io
sudo systemctl start nginx
```

📌 **설명:**

standalone 모드는 Certbot이 직접 80포트를 사용해야 한다.

그러나 Nginx가 이미 해당 포트를 점유 중이어서 인증 요청을 받을 수 없어 실패한 상황이다.

Nginx를 잠시 내려야만 인증이 정상 진행된다.

---

### 😅 실수 3 — 기존 인증서 경로를 그대로 Nginx에 사용

```
ssl_certificate /etc/letsencrypt/live/3.39.55.120.nip.io/fullchain.pem;
```

✅ **정답:**

```
ssl_certificate /etc/letsencrypt/live/44.223.10.161.nip.io/fullchain.pem;
```

📌 **설명:**

이전 실습에서 사용하던 도메인 경로가 남아 있어서

Nginx가 존재하지 않는 인증서 파일을 읽다 실패했다.

HTTPS 설정은 반드시 “현재 발급된 도메인” 기준으로 경로를 일치시켜야 한다.

---

### 😅 실수 4 — 갱신(dry-run) 실패를 실제 오류로 오해함

```bash
sudo certbot renew --dry-run
# → Could not bind TCP port 80
```

✅ **정답:**

- dry-run 실패는 **갱신 테스트** 문제일 뿐이다.
- 현재 인증서는 정상 상태이며 실제 HTTPS 동작에는 영향이 없다.
- standalone 방식은 갱신 시에도 Nginx를 잠시 내려야 한다.

📌 **설명:**

dry-run은 단순 시뮬레이션이며

실제 인증서가 문제 있는 것은 아니다.

테스트 실패는 80포트 충돌로 인한 정상적인 동작이다.

---

### 📌 요약 포인트

- Certbot 설치 시 패키지 철자 중요
- standalone 방식은 Nginx 중지 필수
- Nginx SSL 경로는 “현재 도메인”과 정확히 일치해야 함
- dry-run 실패는 갱신 테스트 문제이며 서비스 장애가 아님
- HTTPS 활성화 → http 요청은 301 리다이렉트가 정상