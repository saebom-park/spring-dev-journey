# [API-4단계] API 문서화 & 테스트 (swagger-postman)

> 이번 단계에서는 API 명세 부재 문제를 해결합니다.
> 
> 
> Swagger(OpenAPI)로 요청/응답 스펙을 문서화하고,
> 
> Postman으로 테스트 시나리오를 관리하는 방법을 학습합니다.
> 
> 이로써 **개발자·프론트·QA가 공유할 수 있는 단일 API 소스**를 완성합니다.
> 

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| Swagger(OpenAPI) | API 스펙을 자동으로 문서화하고, UI로 확인·테스트 가능 |
| springdoc-openapi | Spring Boot 3에서 Swagger UI 제공하는 공식 스타터 |
| API 문서화 목적 | 팀원/외부와의 협업, 자동화된 문서 관리, 명세 기반 개발 |
| Postman | API 테스트/시나리오 저장/협업 도구 |
| 컬렉션(Collection) | API 호출 모음을 저장/공유할 수 있는 단위 |
| 실무 포인트 | “코드와 문서 불일치” 문제 해결 → Swagger로 자동 관리 |

---

## 🧾 예시 코드 (Habit)

### build.gradle 의존성 추가

```
dependencies {
    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0'
}

```

---

### Swagger 기본 설정 (선택)

```java
package com.springlab19.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Habit API")
                        .description("Habit API 명세서 (실습 예시)")
                        .version("v1.0"));
    }
}

```

---

### Swagger UI 접근 경로

- 실행 후: `http://localhost:8080/swagger-ui/index.html`
- Habit API 전체 엔드포인트 자동 문서화

---

### Swagger 화면 예시

- **POST /api/habits** → 요청 DTO, 응답 DTO, 상태코드 확인 가능
- **DELETE /api/habits/{id}** → 204 응답 확인

---

## 📌 포인트 요약

- Swagger = API 명세의 **자동화된 단일 소스**
- Postman = API 호출 시나리오의 **테스트 저장소**
- Swagger로 요청/응답 구조, 상태코드, 예외 포맷 확인 가능
- Postman으로 실제 호출, 인증/파라미터, 반복 테스트 관리
- “코드 따로, 문서 따로” → ❌ → 코드 기반 자동 문서화 → ✅

---

## 🧪 실습 미션 (Diary)

🎯 목표: **Diary API를 Swagger와 Postman으로 문서화/테스트한다.**

1. `build.gradle`에 `springdoc-openapi-starter-webmvc-ui` 추가
2. `SwaggerConfig` 작성 (`Diary API`, `v1.0`)
3. 실행 후 Swagger UI에서 Diary API 전체 엔드포인트 확인
4. Postman Collection 생성
    - Diary 생성/조회/수정/삭제 시나리오 추가
    - 잘못된 요청/예외 응답까지 포함
5. 팀원에게 Collection을 공유할 수 있다고 가정하고 저장