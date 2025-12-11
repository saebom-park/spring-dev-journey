# [DEPLOY-4단계] GitHub Actions CI/CD 자동 배포 (ci-cd)

> 로컬 → EC2 수동 배포를 넘어,
> 
> 
> **GitHub에 push만 하면 자동 빌드 & 자동 배포되는 환경**을 만든다.
> 
> Spring Boot + EC2 + Nginx + HTTPS 환경 위에 **CI/CD 자동화**를 얹는 단계.
> 

---

## 💡 핵심 개념 요약

| 개념 | 설명 | 왜 필요한가 |
| --- | --- | --- |
| CI (Continuous Integration) | GitHub에 push될 때 자동 빌드/테스트 수행 | 빌드 오류를 조기에 발견 |
| CD (Continuous Deployment) | 빌드된 산출물을 서버로 자동 배포 | 수동 배포 없이 “push = 배포” |
| GitHub Actions | GitHub 제공 자동 실행 워크플로우 | CI/CD 파이프라인 구성의 핵심 |
| SSH 배포 | 워크플로우가 EC2에 접속해 파일 업로드·재시작 | GitHub → EC2 직접 배포가 가능해짐 |
| 환경변수(Secrets) | 민감정보(KEY, HOST 등)를 GitHub에 암호화 저장 | 안전한 CI/CD 필수 요소 |

---

## 🧾 예시 코드 / 설정

### 1) GitHub Secrets 등록

GitHub Repository → **Settings → Secrets → Actions**

아래 값 3개 등록:

| Key | 내용 |
| --- | --- |
| `EC2_HOST` | EC2 퍼블릭 IP |
| `EC2_USER` | ubuntu |
| `EC2_KEY` | PEM 키 파일 내용 전체(복붙) |

> ❗ PEM 내용은 그대로 복붙해야 한다.
> 
> 
> -----BEGIN RSA PRIVATE KEY----- 포함 전체.
> 

---

### 2) GitHub Actions 워크플로우 생성

**`.github/workflows/deploy.yml`**

```yaml
name: Deploy to EC2

on:
  push:
    branches: [ "main" ]

jobs:
  deploy:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout repository
        uses: actions/checkout@v4

      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: '17'

      - name: Gradle build
        run: |
          chmod +x gradlew
          ./gradlew clean build -x test

      - name: Copy artifact to EC2
        uses: appleboy/scp-action@v0.1.7
        with:
          host: ${{ secrets.EC2_HOST }}
          username: ${{ secrets.EC2_USER }}
          key: ${{ secrets.EC2_KEY }}
          source: "build/libs/*.jar"
          target: "/opt/app/springlab23/"

      - name: Restart service on EC2
        uses: appleboy/ssh-action@v1.0.3
        with:
          host: ${{ secrets.EC2_HOST }}
          username: ${{ secrets.EC2_USER }}
          key: ${{ secrets.EC2_KEY }}
          script: |
            sudo systemctl restart springlab23
```

---

## 📌 포인트 요약

- GitHub Actions = **push → 자동 빌드 → EC2 배포 → 서비스 재시작**
- PEM 키는 GitHub Secrets에 **그대로 복붙**
- 산출물(jar)을 EC2 디렉토리 `/opt/app/springlab23/` 로 업로드
- 배포 후 반드시 `systemctl restart springlab23`
- Nginx/HTTPS는 이미 구성됨 → 80/443로 자동 서비스

---

## 🧪 실습 미션

🎯 **목표:**

push 하면 자동으로 서버가 재배포되는 “완전 자동 배포” 구축.

1. GitHub repo 생성 → EC2 프로젝트 push
2. GitHub Secrets 3개 등록 (`EC2_HOST`, `EC2_USER`, `EC2_KEY`)
3. `.github/workflows/deploy.yml` 생성
4. `main` 브랜치에 커밋 & push
5. Actions 탭에서 빌드/배포 로그 확인
6. 브라우저에서 `https://<domain>/hello` 응답 확인

> 이 단계가 성공하면 로컬에서 빌드/배포 과정이 완전히 사라짐.
> 
> 
> Push만 해도 자동으로 서비스가 배포되는 실무 CI/CD 완성.
>