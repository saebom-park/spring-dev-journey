# [DEPLOY-4단계] 질문노트: GitHub Actions 자동 배포 (ci-cd)

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: GitHub Actions + EC2 자동 배포 workflow 기준)
> 

---

### 💡 1. 왜 `working-directory`를 꼭 넣어야 했어?

| 질문 | 답변 요약 |
| --- | --- |
| 루트에서 빌드하면 안 돼? | Actions는 기본적으로 **레포지토리 최상위**에서 명령을 실행해. |
| 왜 실패했는데? | 우리가 만든 Gradle 프로젝트는 `/06-deployment` 내부에 있어서, root에는 `gradlew`가 없었어. |
| 그래서 어떤 문제가 생겼어? | `chmod +x gradlew` → 파일이 없어서 바로 실패. |
| 해결 방법은? | `working-directory: 06-deployment` 로 워크플로우가 프로젝트 폴더 안에서 실행되도록 위치를 고정해야 해. |

---

### 💡 2. `/opt/app/springlab23`에서 왜 Permission denied가 떴던 거야?

| 질문 | 답변 요약 |
| --- | --- |
| ubuntu 계정으로 접속했는데 왜 못 써? | 그 디렉토리의 소유자가 **spring** 이었기 때문이야. |
| 그럼 어떤 오류가 나? | `scp-action`이 파일을 생성하려고 할 때 권한이 없어서 `Permission denied` 발생. |
| 어떻게 해결했어? | 디렉토리 소유자를 ubuntu로 변경. `sudo chown -R ubuntu:ubuntu /opt/app/springlab23` |
| 이건 실무에서 흔해? | 아주 흔해! 배포 디렉토리는 배포 계정(ubuntu 또는 deploy user)으로 맞추는 게 기본 흐름이야. |

---

### 💡 3. Secrets를 쓰는 이유가 뭐야? 그냥 YAML에 쓰면 안 돼?

| 질문 | 답변 요약 |
| --- | --- |
| 그냥 deploy.yml 안에 IP/계정/키 넣으면 안 됨? | ❌ 절대 안 됨. 프라이빗 키는 유출되면 서버가 털려. |
| Secrets는 뭐가 달라? | GitHub이 암호화 저장 + workflow 외에서는 누구도 내용을 볼 수 없음. |
| workflow에서 어떻게 쓰는 거야? | `${{ secrets.EC2_HOST }}` 이런 식으로 참조해. |
| 완전히 안전한 거야? | GitHub Secrets는 업계 표준 방식이라 매우 안전한 편. |

---

### 💡 4. 왜 `EC_USER` 때문에 SSH가 안 됐던 거야?

| 질문 | 답변 요약 |
| --- | --- |
| 오타 하나로 SSH가 안 돼? | 맞아. secret 이름이 다르면 GitHub는 “빈 값”을 넘겨. |
| 그럼 어떤 오류가 발생해? | `Host key verification failed`, `Permission denied (publickey)` 같은 SSH 오류 발생. |
| 해결은? | Secrets 이름을 workflow와 정확히 일치시키기. (`EC2_USER`) |

---

### 💡 5. scp-action에서 파일이 “없다”고 나오는 이유는?

| 질문 | 답변 요약 |
| --- | --- |
| 왜 실제로는 파일이 있는데 못 찾는 거야? | workflow가 root 기준에서 경로를 찾고 있어서 그랬어. |
| build/libs는 어디에 떨어지는데? | `/06-deployment/build/libs/*.jar` |
| 해결 방법은? | `source: "06-deployment/build/libs/*.jar"` 처럼 정확한 상대경로를 지정해야 했어. |
| scp-action은 경로에 민감해? | 엄청 민감함. 대부분의 배포 오류는 경로 mismatch로 발생해. |

---

### 🌱 정리 키워드

- working-directory = Actions 실행 위치
- Permission denied = 디렉토리 소유자 mismatch
- Secrets = 민감정보 보호용 필수 요소
- scp-action = 경로/권한 둘 다 정확해야 동작
- systemctl restart = SSH 내부에서 실행하는 명령