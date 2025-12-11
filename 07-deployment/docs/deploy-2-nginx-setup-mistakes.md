# [DEPLOY-2단계] 실수노트

> 💻 실습 환경: EC2 (Ubuntu 22.04) + Nginx + Spring Boot
> 
> 
> 💡 주제: 리버스 프록시 설정 및 방화벽 구성
> 

---

### 😅 실수 1 — `sites-avilable` 경로 오타

```bash
sudo ln -s /etc/nginx/sites-avilable/springlab.conf /etc/nginx/sites-enabled/springlab.conf
```

✅ **정답:**

```bash
sudo ln -s /etc/nginx/sites-available/springlab.conf /etc/nginx/sites-enabled/springlab.conf
```

📌 **설명:**

`available`의 철자 오타(`avilable`)로 인해 원본 파일을 찾지 못해

`nginx -t` 실행 시 `"No such file or directory"` 에러가 발생.

정확한 경로는 항상 `/etc/nginx/sites-available` 이어야 한다.

---

### 😅 실수 2 — `.swp` 파일 충돌로 편집 불가

```bash
sudo vim /etc/nginx/sites-available/springlab.conf
# → "Another program may be editing this file" 경고 발생
```

✅ **정답:**

```bash
sudo rm /etc/nginx/sites-available/.springlab.conf.swp
```

또는

```bash
sudo vim -r /etc/nginx/sites-available/springlab.conf
:wq
```

📌 **설명:**

Vim은 파일 편집 중 자동으로 `.swp` 임시파일을 생성하며

비정상 종료 시 삭제되지 않는다.

다음 편집 시 충돌 경고가 나올 수 있으므로, 필요 시 삭제하거나 `vim -r`로 복구한다.

---

### 😅 실수 3 — `File exists` 에러 (기존 링크 중복)

```bash
sudo ln -s /etc/nginx/sites-available/springlab.conf /etc/nginx/sites-enabled/springlab.conf
# → ln: failed to create symbolic link ... : File exists
```

✅ **정답:**

```bash
sudo rm -f /etc/nginx/sites-enabled/springlab.conf
sudo ln -s /etc/nginx/sites-available/springlab.conf /etc/nginx/sites-enabled/springlab.conf
```

📌 **설명:**

기존에 깨진 링크가 남아있을 때 발생하는 에러다.

심볼릭 링크는 한 번에 하나만 존재할 수 있으므로,

새로 만들기 전 반드시 기존 링크를 삭제해야 한다.

---

### 😅 실수 4 — `ufw status` 가 inactive 상태

```bash
sudo ufw allow 80/tcp
sudo ufw allow 443/tcp
sudo ufw status
# → Status: inactive
```

✅ **정답:**

```bash
sudo ufw enable
sudo ufw status
```

📌 **설명:**

규칙은 추가되었지만 방화벽 자체가 비활성화된 상태.

`sudo ufw enable` 명령으로 방화벽을 활성화해야

포트 허용 규칙이 실제로 적용된다.

---

### 😅 실수 5 — `curl http://localhost` 404 응답

```bash
curl -i http://localhost
# HTTP/1.1 404 Not Found
```

✅ **정답:**

```bash
curl -i http://localhost/hello
# HTTP/1.1 200 OK
# Deploy Build Test!
```

📌 **설명:**

Nginx ↔ Spring Boot 연결은 정상 작동 중이지만

Spring Boot에 `/` 경로를 처리하는 컨트롤러가 없어 404가 발생한 것.

즉, 네트워크 연결 실패가 아닌 단순 라우팅 미구현 상황이다.

---

### 📌 요약 포인트

- `sites-available` 철자 오타는 가장 흔한 경로 실수
- `.swp` 파일은 비정상 종료 시 자동 생성 → 삭제 또는 복구
- `ln -s` 실행 전에는 기존 링크 존재 여부 확인 (`ls -l`)
- `ufw enable` 없이 규칙만 등록하면 적용되지 않음
- 404는 연결 실패가 아닌 Spring 라우팅 미구현