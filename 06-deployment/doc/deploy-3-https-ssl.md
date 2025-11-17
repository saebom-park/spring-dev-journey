# [DEPLOY-3단계] HTTPS / SSL 인증 (https-ssl)

> “HTTP로 열린 서버에 ‘보안’을 입히는 단계입니다.”
> 
> 
> EC2에서 구동 중인 Spring Boot + Nginx 서버에
> 
> **Let’s Encrypt** 무료 SSL 인증서를 적용해 HTTPS로 완성합니다.
> 

---

## 💡 핵심 개념 요약

| 구분 | 개념 | 설명 |
| --- | --- | --- |
| SSL / TLS | Secure Socket Layer / Transport Layer Security | HTTP 통신을 암호화해 도청·위조를 방지 |
| HTTPS | HTTP + SSL | 포트 443 사용, 인증서 기반 암호화 통신 |
| 인증서 (Certificate) | 공개키 기반 구조(PKI)의 신뢰 서명 파일 | 서버 신원을 보장하고 통신을 암호화함 |
| Let’s Encrypt | 무료 SSL 인증 기관 (CA) | 도메인 또는 IP 기반 자동 인증 지원 |
| Certbot | Let’s Encrypt 인증서 발급 도구 | 발급·갱신 자동화 지원 (`apt install certbot`) |

> 핵심 요약:
> 
> 
> SSL은 ‘암호화된 HTTP’, HTTPS는 ‘보안이 적용된 표준 포트(443)’.
> 
> Let’s Encrypt + Certbot 조합으로 **무료 자동 인증 환경**을 구성한다.
> 

---

## 🧾 실습 단계

### 1️⃣ Certbot 설치

```bash
sudo apt update
sudo apt install certbot python3-certbot-nginx -y
```

---

### 2️⃣ 인증서 발급 (도메인 없는 버전)

> EC2 IP 기반 실습용 — 실무에서는 반드시 도메인 기반으로 진행해야 함.
> 

```bash
sudo certbot certonly --standalone -d <EC2_IP>.nip.io --register-unsafely-without-email
```

- `<EC2_IP>`: 실제 EC2 인스턴스의 공인 IP
- `nip.io`: 임시 도메인 자동 매핑 서비스 (예: 3.39.55.120.nip.io)
- 성공 시 `/etc/letsencrypt/live/<도메인>/fullchain.pem` 경로에 인증서 생성됨

---

### 3️⃣ Nginx HTTPS 설정 추가

```bash
sudo nano /etc/nginx/sites-available/springlab.conf
```

```
server {
    listen 80;
    server_name 3.39.55.120.nip.io;

    return 301 https://$host$request_uri;
}

server {
    listen 443 ssl;
    server_name 3.39.55.120.nip.io;

    ssl_certificate /etc/letsencrypt/live/3.39.55.120.nip.io/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/3.39.55.120.nip.io/privkey.pem;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

> 80포트는 HTTP 요청을 받고 HTTPS(443)로 리다이렉트,
> 
> 
> 443포트 블록은 SSL 인증서를 통해 암호화 통신을 처리한다.
> 

---

### 4️⃣ Nginx 설정 테스트 및 재시작

```bash
sudo nginx -t
sudo systemctl restart nginx
```

- `syntax is ok / test is successful` 이 뜨면 정상
- 설정 변경 후 항상 `nginx -t` → `systemctl restart nginx` 순서로 점검

---

### 5️⃣ HTTPS 연결 확인

```bash
curl -I https://<도메인 or IP>.nip.io
```

- 응답 코드가 `200` 또는 `302`면 성공
- 브라우저 접속 시 🔒 자물쇠 아이콘 확인

---

## 📌 포인트 정리

- `Certbot + Nginx` 플러그인은 **자동 설정** 지원하지만,
    
    도메인 없는 경우에는 `--standalone` 수동 발급을 사용해야 함.
    
- 인증서 유효기간: **90일** (자동 갱신 설정 필수)
- 자동 갱신 명령어:
    
    ```bash
    sudo certbot renew --dry-run
    ```
    
- 실무에서는 `cron` 또는 `systemd timer`를 등록해 자동 갱신 루틴 구성
- HTTPS 리다이렉트 설정 시 80 → 443 블록 구문 순서 주의

> ✅ 핵심 한 줄:
> 
> 
> “HTTP(80) → HTTPS(443)로 전환해 보안 통신을 완성하고,
> 
> 인증서 갱신 자동화를 통해 무중단 운영을 유지한다.”
> 

---

## 🧪 실습 미션

🎯 **목표:**

Nginx 리버스 프록시에 SSL 인증서를 적용해 HTTPS 통신을 완성한다.

1. Certbot 설치 (`apt install certbot python3-certbot-nginx`)
2. `nip.io` 임시 도메인을 이용해 인증서 발급
3. `/etc/nginx/sites-available/springlab.conf`에 SSL 설정 추가
4. `http → https` 리다이렉트 정상 작동 확인
5. `curl -I https://<도메인>` 응답코드 점검
6. 인증서 경로 및 자동 갱신(`certbot renew --dry-run`) 테스트

> 참고:
> 
> - 도메인이 있다면 `sudo certbot --nginx -d example.com` 명령으로 자동 설정 가능
> - 발급된 인증서는 `/etc/letsencrypt/live/` 경로에서 관리됨

---

🌿 **정리 멘트**

DEPLOY-1에서 서버를 띄우고,

DEPLOY-2에서 포트를 숨겼다면,

이제 DEPLOY-3에서는 **보안을 완성**한다.

“HTTP → HTTPS”, 이것이 바로 **실무 서버의 기본 자격 요건**이다. 🔒