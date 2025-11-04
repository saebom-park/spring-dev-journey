# [DEPLOY-2단계] Nginx 리버스 프록시 해설 (nginx-setup-details)

> 이 문서는 DEPLOY-2 수업자료의 💡 핵심 개념 요약을 실제 원리와 비유로 풀어 설명한 버전입니다.
>  
> 배포 초심자도 ‘Nginx가 정확히 무슨 역할을 하는지’를 한눈에 이해할 수 있도록 구성했습니다.

---

## 🌍 1️⃣ Nginx는 ‘건물의 안내데스크’다

EC2 서버 안에는 Spring Boot 애플리케이션이 8080 포트에서 동작 중입니다.
하지만 사용자는 80(HTTP)이나 443(HTTPS) 포트로만 접근합니다.

👉 그래서 중간에 Nginx가 ‘대리인’으로 서 있습니다.

```
사용자 (80포트 요청)
     ↓
[Nginx]  →  Spring Boot (8080 포트)
```

> **즉, Nginx는 외부 요청을 대신 받아서 내부 Spring Boot로 전달하는 ‘문지기’입니다.**

---

## 🧱 2️⃣ 왜 Nginx를 써야 할까?

| 이유 | 설명 |
|------|------|
| **1. 포트를 숨기기 위해** | 8080은 비표준 포트입니다. 외부 노출 시 주소가 복잡하고 보안상 좋지 않습니다. |
| **2. HTTPS 연결을 위해** | SSL 인증은 80/443 포트 기준으로 동작합니다. Spring Boot 대신 Nginx가 처리하면 훨씬 간단합니다. |
| **3. 부하를 분산하기 위해** | 서버가 여러 개일 경우 Nginx가 자동으로 트래픽을 나눌 수 있습니다. |
| **4. 정적파일 처리 효율** | 이미지·CSS 같은 파일은 Spring보다 Nginx가 훨씬 빠르게 응답합니다. |

> 요약: **Nginx = 트래픽 관리 + 보안 + 효율**

---

## ⚙️ 3️⃣ 리버스 프록시(reverse proxy)란?

프록시는 ‘대신 처리해주는 것’을 뜻합니다.

- **Forward Proxy** → 내부에서 외부로 요청을 대리 (회사 네트워크 등)
- **Reverse Proxy** → 외부에서 내부 서버로 요청을 대리 (**Nginx 역할**)

```
[사용자] → [Nginx] → [Spring Boot]
```

> 사용자는 Spring Boot의 존재를 모르고, 오직 Nginx에게만 요청을 보냅니다.

---

## 🧩 4️⃣ proxy_pass와 헤더 4종의 의미

```nginx
location / {
    proxy_pass http://localhost:8080;
    proxy_set_header Host $host;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header X-Forwarded-Proto $scheme;
}
```

| 설정 | 의미 | 이유 |
|------|------|------|
| **proxy_pass** | 요청을 전달할 대상 (Spring Boot 서버 주소) | Nginx가 대신 호출할 곳 지정 |
| **Host** | 원래 사용자가 요청한 도메인 | 리다이렉트나 HTTPS 전환 시 필요 |
| **X-Real-IP** | 실제 접속한 사용자 IP | 로그에 진짜 IP 기록 가능 |
| **X-Forwarded-For** | 여러 프록시를 거친 경우 IP 체인 | 추적용 |
| **X-Forwarded-Proto** | HTTP 또는 HTTPS 여부 | HTTPS 리다이렉트 정확성 보장 |

> proxy_pass는 “어디로 보낼지”, 헤더는 “누가 보냈는지” 정보를 전달합니다.

---

## 🧩 5️⃣ sites-available / sites-enabled 구조

```
/etc/nginx/sites-available/  ← 설정 파일 저장
/etc/nginx/sites-enabled/    ← 실제 사용 중인 설정 (링크)
```

- **sites-available**: 작성용 폴더 (준비된 설정들)
- **sites-enabled**: 실제로 활성화된 설정 (링크로 연결됨)

활성화는 이렇게 합니다 👇

```bash
sudo ln -s /etc/nginx/sites-available/springlab.conf /etc/nginx/sites-enabled/
```

> 기본 conf를 삭제하지 않으면 `/` 요청이 기본 페이지(Nginx Welcome)로 가버릴 수 있습니다.

```bash
sudo rm /etc/nginx/sites-enabled/default
```

---

## 🔥 6️⃣ 요청 흐름 전체 그림

```
[사용자 브라우저]   http://<EC2_IP>
           ↓ (80포트)
        [Nginx 서버]
           ↓ (8080포트 내부 전달)
      [Spring Boot 앱]
           ↓
         [응답 반환]
```

> 요청은 80으로 들어오고, Nginx가 내부의 8080(Spring Boot)으로 전달한 후
> 다시 사용자에게 결과를 돌려줍니다.

---

## 🪄 7️⃣ 헷갈리는 포인트 요약

| 질문 | 핵심 정리 |
|------|-----------|
| “왜 localhost야?” | 같은 EC2 안에서 Nginx와 Spring이 함께 실행 중이기 때문입니다. |
| “왜 포트가 다르지?” | 80은 외부, 8080은 내부 통신용입니다. |
| “nginx -t 는 뭐야?” | 설정 문법 검사용 명령어 (재시작 전 반드시 실행!) |
| “systemctl restart nginx” | 수정한 설정을 반영하기 위한 재시작 명령 |
| “error.log는 왜 중요해?” | 설정 에러나 502(백엔드 연결 실패) 원인 파악의 핵심 로그 |

---

> 💡 핵심 한 줄 요약:  
> **Nginx는 외부 요청을 대신 받아 내부 애플리케이션으로 전달하는 서버이며,  
> 이를 통해 보안, 확장성, 유지보수성을 모두 확보할 수 있다.**
