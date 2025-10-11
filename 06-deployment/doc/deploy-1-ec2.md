# [DEPLOY-1단계] EC2 서버 생성 & JAR 수동 배포 (ec2)

> 로컬에서 새 프로젝트를 최소구성으로 빌드하고,
> 
> 
> AWS EC2에 **수동 배포**(jar 전송 + systemd 등록)까지 완료한다.
> 

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| EC2 | AWS의 가상 서버. SSH로 접속해서 애플리케이션 실행/관리 |
| JAR 배포 | 로컬에서 jar 빌드 → 서버에 복사(scp) → `java -jar` 실행 |
| Profile | `--spring.profiles.active=prod` 등 환경별 설정 분리 적용 |
| systemd | 리눅스 서비스 관리자. 부팅 시 자동 시작, 로그/상태 관리 |
| 보안 그룹 | EC2 방화벽. 22(SSH), 80/443(Nginx), 8080(직접확인용) 포트 설정 |

---

## 🧾 예시 코드 / 설정

### 1) 로컬: springlab23 최소 프로젝트

**`build.gradle`**

```
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.3.3'
    id 'io.spring.dependency-management' version '1.1.6'
}

group = 'com.springlab23'
version = '1.0.0'
java {
    toolchain { languageVersion = JavaLanguageVersion.of(17) }
}

repositories { mavenCentral() }

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') { useJUnitPlatform() }

```

**`src/main/java/com/springlab23/Springlab23Application.java`**

```java
package com.springlab23;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springlab23Application {
    public static void main(String[] args) {
        SpringApplication.run(SPRINGLAB23Application.class, args);
    }
}

```

**`src/main/java/com/springlab23/controller/HelloController.java`**

```java
package com.springlab23.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Deploy Build Test!";
    }
}

```

> ⚠️ 클래스/패키지 오타 주의: Springlab23Application 이름과 package 경로가 group과 1:1 이어야 해.
> 

**`src/main/resources/application.yml` (공통)**

```yaml
spring:
  application:
    name: springlab23
  profiles:
    active: dev

server:
  port: 8080

logging:
  level:
    root: info

```

**`src/main/resources/application-dev.yml` (개발)**

```yaml
logging:
  level:
    org.springframework.web: debug

```

**`src/main/resources/application-prod.yml` (운영)**

```yaml
server:
  port: 8080

logging:
  level:
    root: info

```

**로컬 빌드**

```bash
./gradlew clean build
# 산출물: build/libs/springlab23-1.0.0.jar

```

---

### 2) AWS: EC2 준비 (Ubuntu 22.04 가정)

**보안 그룹 포트**

- 22(SSH), 80(HTTP), 443(HTTPS), 8080(직접 확인용) — 인바운드 허용

**접속 & 기본 설정**

```bash
ssh -i <YOUR_KEY>.pem ubuntu@<EC2_HOST>

# Java 17
sudo apt update -y
sudo apt install -y openjdk-17-jre

# 앱 디렉토리 / 사용자
sudo useradd -m -s /bin/bash spring || true
sudo mkdir -p /opt/app/springlab23
sudo chown -R spring:spring /opt/app/springlab23

# (선택) 시간대 설정
sudo timedatectl set-timezone Asia/Seoul

```

---

### 3) JAR 업로드 & 1회 실행 확인

**로컬에서 JAR 전송**

```bash
scp -i <YOUR_KEY>.pem build/libs/springlab23-1.0.0.jar ubuntu@<EC2_HOST>:/home/ubuntu/
ssh -i <YOUR_KEY>.pem ubuntu@<EC2_HOST> "sudo mv /home/ubuntu/springlab23-1.0.0.jar /opt/app/springlab23/ && sudo chown spring:spring /opt/app/springlab23/springlab23-1.0.0.jar"

```

**서버에서 수동 실행(프로파일 prod)**

```bash
ssh -i <YOUR_KEY>.pem ubuntu@<EC2_HOST>
sudo -u spring bash -c "cd /opt/app/springlab23 && nohup java -jar springlab23-1.0.0.jar --spring.profiles.active=prod > app.log 2>&1 &"
tail -f /opt/app/springlab23/app.log

```

**헬스 체크**

```bash
curl http://127.0.0.1:8080/hello
# → Deploy Build Test!

```

> 외부에서 바로 보고 싶다면 보안그룹에 8080 오픈 필요(임시).
> 
> 
> 다음 단계에서 Nginx 리버스 프록시(80/443)로 전환할 예정.
> 

---

### 4) systemd 서비스 등록 (부팅 자동 시작)

**`/etc/systemd/system/springlab23.service`**

```
[Unit]
Description=Spring Boot - springlab23
After=network.target

[Service]
User=spring
WorkingDirectory=/opt/app/springlab23
ExecStart=/usr/bin/java -jar /opt/app/springlab23/springlab23-1.0.0.jar --spring.profiles.active=prod
SuccessExitStatus=143
Restart=always
RestartSec=5
Environment=JAVA_TOOL_OPTIONS=-XX:+UseContainerSupport

[Install]
WantedBy=multi-user.target

```

**적용 & 기동**

```bash
sudo systemctl daemon-reload
sudo systemctl enable springlab23
sudo systemctl start springlab23
sudo systemctl status springlab23 --no-pager
journalctl -u springlab23 -f

```

---

## 📌 포인트 요약

- **프로젝트는 최소 구성(web + devtools)** 으로 새로 시작 → 배포 학습에만 집중
- **Profiles(dev/prod) 분리**: 로컬은 dev, 서버는 `-spring.profiles.active=prod`
- **수동 배포 순서**: 로컬 빌드 → scp → nohup 테스트 → systemd 등록
- EC2 **보안그룹 포트** 정확히 열기(22/80/443, 임시 8080)
- **systemd**로 서비스 등록하면 부팅 시 자동 실행 + 로그/재시작 관리
- 다음 단계에서 **Nginx(80/443) + HTTPS** 로 외부 공개 전환

---

## 🧪 실습 미션

🎯 **목표:** EC2에 springlab23 JAR를 수동 배포하고, `/hello` 응답을 서버에서 정상 확인한다.

1. `06-deployment/springlab23` 새 프로젝트 생성 (group=`com.springlab23`)
2. 위 예시의 **build.gradle / Application / Controller / yml 3종** 작성
3. 로컬에서 `./gradlew clean build` 로 JAR 생성 확인
4. AWS 콘솔에서 **Ubuntu 22.04 EC2 인스턴스 생성**, 보안그룹(22/80/443/8080) 설정
5. `ssh -i`로 접속, **JDK 17 설치** 및 `/opt/app/springlab23` 디렉토리 준비
6. `scp`로 JAR 전송 → `nohup java -jar ... --spring.profiles.active=prod` 1회 실행 확인
7. `systemd` 서비스 등록 → `systemctl enable/start/status`로 서비스화
8. `curl http://127.0.0.1:8080/hello` 로 최종 확인 **✅ 성공**