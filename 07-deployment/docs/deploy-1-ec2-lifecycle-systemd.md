# springlab23 EC2 서버 운영 매뉴얼 (systemd 버전)

---

## 🧭 1️⃣ 최초 실행 매뉴얼 (서버 새로 만들 때)

> 📍 목적: 새 EC2 서버에 Spring Boot 앱을 배포하고 **systemd 서비스로 등록**

---

### ✅ 단계별 절차

### ① AWS EC2 인스턴스 생성

1. **리전:** 미국 동부 (버지니아 북부) `us-east-1`  
2. **AMI:** Ubuntu Server 22.04 LTS (64-bit x86)  
3. **인스턴스 유형:** t3.micro (Free Tier)  
4. **키 페어:** 새로 생성 → `springlab-key.pem`  
5. **보안 그룹 포트:**  
   SSH(22), HTTP(80), HTTPS(443), Custom TCP(8080)  
6. **스토리지:** 기본 8GB 그대로  
7. **생성 후 퍼블릭 IPv4 주소 확인**

---

### ② 서버 접속

```bash
ssh -i "C:\Users\새봄\Downloads\springlab-key.pem" ubuntu@<EC2_PUBLIC_IP>
```

---

### ③ 환경 세팅

```bash
sudo apt update -y
sudo apt install -y openjdk-17-jre
sudo useradd -m -s /bin/bash spring || true
sudo mkdir -p /opt/app/springlab23
sudo chown -R spring:spring /opt/app/springlab23
sudo timedatectl set-timezone Asia/Seoul
```

---

### ④ JAR 파일 업로드 (로컬 PowerShell에서)

```bash
scp -i "C:\Users\새봄\Downloads\springlab-key.pem" build/libs/springlab23-1.0.0.jar ubuntu@<EC2_PUBLIC_IP>:/home/ubuntu/
```

---

### ⑤ 서버에서 파일 이동

```bash
sudo mv /home/ubuntu/springlab23-1.0.0.jar /opt/app/springlab23/
sudo chown spring:spring /opt/app/springlab23/springlab23-1.0.0.jar
```

---

### ⑥ systemd 서비스 파일 생성

```bash
sudo tee /etc/systemd/system/springlab23.service > /dev/null <<'EOF'
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
EOF
```

---

### ⑦ 서비스 등록 및 실행

```bash
sudo systemctl daemon-reload
sudo systemctl enable springlab23
sudo systemctl start springlab23
sudo systemctl status springlab23 --no-pager
```

> ✅ `Active: active (running)` 나오면 성공  
> 💬 문제 시 `sudo journalctl -u springlab23 -f` 로 로그 확인

---

### ⑧ 브라우저에서 확인

```
http://<EC2_PUBLIC_IP>:8080/hello
```

→ “Deploy Build Test!” 응답이 나오면 성공 🎉  

> ⚠️ 보안 그룹에 8080 인바운드 임시로 열기  
> (나중에 Nginx 80/443 구성 시 닫기)

---

## ⚙️ 2️⃣ 추후 실행 매뉴얼 (서버 이미 있음)

> 📍 목적: EC2 재시작 또는 재부팅 후 앱이 자동 실행되는지 확인

---

### ✅ 절차

### ① EC2 인스턴스 시작

AWS 콘솔 → EC2 → 인스턴스 선택 → **[Start Instance]**

### ② SSH 접속

```bash
ssh -i "C:\Users\새봄\Downloads\springlab-key.pem" ubuntu@<EC2_PUBLIC_IP>
```

### ③ 서비스 상태 확인

```bash
sudo systemctl status springlab23 --no-pager
```

> `active (running)` 이면 자동 실행 정상 ✅

### ④ 로그 확인 (선택)

```bash
sudo journalctl -u springlab23 -n 100 --no-pager
```

### ⑤ 브라우저로 확인

```
http://<EC2_PUBLIC_IP>:8080/hello
```

→ 정상 응답 확인 시 완료 🎯

---

## 🛑 3️⃣ 종료 매뉴얼 (요금 방지용)

> 📍 목적: 서버 정리 및 비용 방지

---

### ✅ 서버 내부에서 앱 종료 (선택)

```bash
sudo systemctl stop springlab23
sudo systemctl status springlab23 --no-pager
```

> `inactive (dead)` 확인되면 서비스 완전 중지 ✅

---

### ✅ AWS 콘솔에서 인스턴스 정리

### ① 인스턴스 중지 (권장)

- AWS 콘솔 → EC2 → 인스턴스 선택  
- 상단 메뉴 → **[Instance state] → [Stop instance]**

→ 서버 전원만 꺼짐 (컴퓨팅 요금 ❌, EBS 저장 요금만 유지)

---

### ② 인스턴스 완전 종료 (선택)

- 더 이상 서버가 필요 없다면  
  → **[Instance state] → [Terminate instance]**  
  (되돌릴 수 없음 ❌, IP도 삭제됨)

---

### ③ PEM 키 보관

- `springlab-key.pem`은 다음 실습용으로 유지  
- 절대 삭제 금지 🚫

---

### 💬 정리표

| 단계 | 작업 | 설명 | 과금 여부 |
| --- | --- | --- | --- |
| 앱 중지 | `sudo systemctl stop springlab23` | EC2 내부 서비스 종료 | - |
| 인스턴스 중지 | AWS 콘솔 → Stop | 서버 전원 꺼짐 | ❌ |
| 인스턴스 종료 | AWS 콘솔 → Terminate | 서버 완전 삭제 | ❌ |
| 실행 중 유지 | 아무 조치 X | 계속 과금 발생 | ⚠️ |

---

## 🌿 한 줄 요약

> 🧩 **systemd 등록:** 한 번만 설정해두면 EC2 부팅 시 자동 실행  
>  
> 🔁 **추후 실행:** EC2 Start 후 자동 활성화 확인  
>  
> 🛑 **종료:** `systemctl stop` + Stop instance (과금 방지)
