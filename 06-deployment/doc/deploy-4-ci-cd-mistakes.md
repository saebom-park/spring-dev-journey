# [DEPLOY-4단계] 실수노트

> 💻 실습 환경: GitHub Actions + EC2 + systemd
> 
> 
> 💡 주제: CI/CD 자동 배포 구성
> 

---

### 😅 실수 1 — Secrets 이름 오타 (`EC_USER` → `EC2_USER`)

📌 **증상**

- SSH 연결 실패
- `Permission denied (publickey)` 발생

📌 **원인**

workflow가 Secrets에 없는 값을 참조해서

계정명이 비어 있는 상태로 SSH 시도 → 당연히 실패.

📌 **해결**

`EC2_USER`로 정확하게 수정.

---

### 😅 실수 2 — Gradle 프로젝트 루트 경로를 workflow에 지정하지 않음

📌 **증상**

```
chmod: cannot access 'gradlew'
```

📌 **원인**

Actions 기본 실행 위치는 root.

하지만 gradlew는 `/06-deployment` 내부에 존재.

📌 **해결**

`working-directory: 06-deployment` 추가.

---

### 😅 실수 3 — 배포 디렉토리 권한 부족으로 artifact 업로드 실패

📌 **증상**

```
Permission denied
tar: Cannot mkdir
```

📌 **원인**

`/opt/app/springlab23` 소유자가 spring 유저 → ubuntu는 쓰기 불가.

📌 **해결**

```
sudo chown -R ubuntu:ubuntu /opt/app/springlab23
sudo chmod -R 755 /opt/app/springlab23
```

---

### 😅 실수 4 — scp source 경로 mismatch

📌 **증상**

```
No such file or directory
```

📌 **원인**

빌드 결과는 `06-deployment/build/libs` 에 있는데

workflow는 `build/libs` 경로만 참조.

📌 **해결**

`source: "06-deployment/build/libs/*.jar"` 로 수정.

---

### 😅 실수 5 — Actions 로그 전체만 보고 정확한 step을 못 찾음

📌 **증상**

원인 파악 지연

📌 **원인**

Workflow는 step 단위로 에러가 찍히는데

전체 로그만 훑어서 문제 위치를 초기에는 못 찾음.

📌 **해결**

- 실패한 step(빨간 step)을 먼저 클릭
- 하단 20줄 정도가 가장 중요한 원인 위치

---

### 📌 요약 포인트

- DEPLOY-4의 핵심 오류는 **경로**와 **권한**이다.
- CI/CD 실패 시 → “어느 step에서 깨졌는가” 먼저 확인
- Secrets 이름, 경로, working-directory는 절대 오타 금지
- 배포 디렉토리는 SSH 접속 유저(ubuntu)가 쓸 수 있어야 한다