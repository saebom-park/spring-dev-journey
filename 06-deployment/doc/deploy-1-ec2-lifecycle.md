# springlab23 EC2 서버 운영 매뉴얼

---

## 🧭 1️⃣ 최초 실행 매뉴얼 (서버 새로 만들 때)

> 📍 목적: 새 EC2 서버에 Spring Boot 앱을 최초로 배포 & 실행
> 

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

```

---

### ④ JAR 파일 업로드 (로컬 PowerShell에서)

```bash
scp -i "C:\Users\새봄\Downloads\springlab-key.pem" build/libs/springlab23-1.0.0.jar ubuntu@<EC2_PUBLIC_IP>:/home/ubuntu/

```

---

### ⑤ 서버에서 파일 이동 & 실행

```bash
sudo mv /home/ubuntu/springlab23-1.0.0.jar /opt/app/springlab23/
sudo chown spring:spring /opt/app/springlab23/springlab23-1.0.0.jar
sudo -u spring bash -c "cd /opt/app/springlab23 && nohup java -jar springlab23-1.0.0.jar --spring.profiles.active=prod > app.log 2>&1 &"
tail -f /opt/app/springlab23/app.log

```

---

### ⑥ 실행 확인

브라우저 접속:

```
http://<EC2_PUBLIC_IP>:8080/hello

```

→ “Deploy Build Test!” 가 나오면 성공 🎉

---

## ⚙️ 2️⃣ 추후 실행 매뉴얼 (서버 이미 있음)

> 📍 목적: EC2를 다시 켜서 기존 JAR로 재실행할 때
> 

### ✅ 절차

### ① EC2 인스턴스 시작

AWS 콘솔 → EC2 → 인스턴스 선택 → **[Start Instance]**

### ② SSH 접속

```bash
ssh -i "C:\Users\새봄\Downloads\springlab-key.pem" ubuntu@<EC2_PUBLIC_IP>

```

### ③ 애플리케이션 실행

```bash
sudo -u spring bash -c "cd /opt/app/springlab23 && nohup java -jar springlab23-1.0.0.jar --spring.profiles.active=prod > app.log 2>&1 &"

```

### ④ 로그 확인

```bash
tail -f /opt/app/springlab23/app.log

```

### ⑤ 브라우저로 확인

```
http://<EC2_PUBLIC_IP>:8080/hello

```

---

## 🛑 3️⃣ 종료 매뉴얼 (요금 방지용)

> 📍 목적: 서버 정리 및 비용 방지
> 

### ✅ 서버 내부에서 앱 종료

```bash
sudo pkill -f 'springlab23'
ps aux | grep springlab23  # 실행 중인지 확인

```

(아무 출력이 안 나오면 ✅ 완전 종료)

---

### ✅ AWS 콘솔에서 인스턴스 정리

### ① 인스턴스 중지 (권장)

- AWS 콘솔 → EC2 → 인스턴스 선택
- 상단 메뉴 → **[Instance state] → [Stop instance]**

→ 서버 전원만 꺼짐 (과금 ❌)

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
| 앱 중지 | `sudo pkill -f 'springlab23'` | 서버 내 앱 종료 | - |
| 인스턴스 중지 | AWS 콘솔 → Stop | 서버 전원 꺼짐 | ❌ |
| 인스턴스 종료 | AWS 콘솔 → Terminate | 서버 완전 삭제 | ❌ |
| 실행 중 유지 | 아무 조치 X | 계속 과금 발생 | ⚠️ |

---

## 🌿 한 줄 요약

> 💻 최초 실행: 새 서버 만들고 jar 업로드
> 
> 
> 🔁 **추후 실행:** 기존 jar 재시작
> 
> 🛑 **종료:** pkill + Stop instance
>