# 📚 Algorithm Doc Structure Standard
### (Phase별 템플릿 적용 기준 · 새봄 공식 가이드)

> 위치: `00-algorithms/doc/algorithm-doc-structure-standard.md`  
> 목적: 모든 Phase에서 **어떤 문서를, 언제, 어떤 템플릿으로** 작성하는지 표준화한다.  
> 원칙: 인강 없이도 문서만으로 이해·학습·복습 가능하도록 **교재형**으로 운영한다.

---

## 0. 범위 & 요약

- **Phase 0 = 문법 체화 단계** → 문제 파일만 작성 (템플릿: `day-file-skeleton.md`)
- **Phase 1~5 = 개념+사고력 단계** → 개념 문서 + 문제 파일 + 주간 회고
  - 개념 문서 템플릿: `templates/concept-v3.md`
  - 문제 파일 템플릿: `templates/day-file-skeleton.md`
  - 주간 회고 템플릿: `templates/weekly-review.md`

---

## 1. 디렉토리 위치 규칙

```
00-algorithms/
├── algorithms-curriculum/
│   ├── phase0-java-fundamentals/
│   ├── phase1-implementation/
│   ├── phase2-data-structure/
│   ├── phase3-graph-search/
│   ├── phase4-advanced/
│   └── phase5-practice/
└── doc/
    ├── algorithm-curriculum-structure.md        # 전체 구조 정리
    ├── algorithm-curriculum-master-schedule.md  # 전체 진도표
    └── templates/
        ├── concept-v3.md
        ├── day-file-skeleton.md
        └── weekly-review.md
```

---

## 2. 파일 네이밍 & 메타 규칙

- **개념 문서:** `concept-<topic>.md` (예: `concept-greedy.md`, `concept-bfs.md`)
- **문제 파일:** `dayNN-<topic>.md` (예: `day08-prefix-basic.md`)
- **주간 회고:** `day07-weekly-review.md`, `day15-weekly-review.md` (Phase 내 Week 마지막)
- **README:** 각 Phase 루트에 진행 현황 표 포함
- **메타 블록(문제 파일 상단):**
  ```markdown
  > 📆 Date: YYYY-MM-DD  
  > ⏱ 설계/구현/디버그: 10m/20m/5m  
  > 💬 난이도: Lv1  
  > 🧭 Phase: N  
  > 🔁 복습일: D+3
  ```

---

## 3. Phase 0 문서 작성 표준

### 3.1 사용 템플릿
- **오직** `templates/day-file-skeleton.md` 사용

### 3.2 구성 원칙
- **개념 요약**은 간결: 문법 키워드 + 사용 예
- **문제 풀이**는 손코딩 위주 (자동완성 금지)
- **비교 포인트**는 스타일·문법 중심
- **질문/실수**는 단순 문법 위주로 기록
- **리마인드**는 I/O→로직→출력 파이프라인 복습

### 3.3 예시
```
phase0-java-fundamentals/day01-basic-grammar/day01-1-even-or-odd.md
```

---

## 4. Phase 1~5 문서 작성 표준

### 4.1 사용 템플릿 (3종)
| 용도 | 템플릿 | 파일명 규칙 | 작성 타이밍 |
|------|--------|--------------|-------------|
| **개념 설명** | `concept-v3.md` | `concept-<topic>.md` | 해당 개념이 **처음 등장하는 날 1회** |
| **문제 풀이** | `day-file-skeleton.md` | `dayNN-<topic>.md` | 매 Day의 각 문제마다 |
| **주간 회고** | `weekly-review.md` | `day07-weekly-review.md` 등 | Week 마지막 날 |

### 4.2 개념 문서(교재형) 작성 규칙
- 4단 구성: **인트로(비유) → 정의/조건 → 시각 흐름 → 사고 질문**
- 예시 코드 5~15줄 내외, **전략/시간복잡도** 명시
- 반례/적용 조건을 **명확히 표기** (그리디/파라매트릭 등)

### 4.3 문제 파일 작성 규칙
- All-in-One 섹션 고정: **💡→🧾→📌→🌱→📒→🐞→🔁**
- **온이 풀이**에는 반드시 전략 요약 + 시간복잡도 포함
- **비교 포인트**는 표로 구체화 (정렬 기준/자료구조/업데이트 타이밍 등)

### 4.4 주간 회고 작성 규칙
- **실수 패턴 표**(원인/해결책) 필수
- **이해 부족 개념**을 다음 주의 개념 문서 보강 주제로 연결
- **속도 기록**(설계/구현/디버그 평균) 집계

---

## 5. 체크리스트

### 5.1 Day 파일 제출 전 체크
- [ ] 메타 블록(날짜/시간/난이도/Phase/복습일) 채움
- [ ] 내 풀이와 온이 풀이가 모두 존재
- [ ] 비교 포인트 표에 **핵심 차이 3개 이상**
- [ ] 질문·실수 섹션에 최소 1개 이상 기록
- [ ] 리마인드에 “다시 풀 전략 한 줄” 기재

### 5.2 개념 문서 제출 전 체크
- [ ] 인트로에 **비유/직관** 포함
- [ ] 정의/조건/검증 포인트가 표로 정리됨
- [ ] 시각 흐름 다이어그램 존재
- [ ] 예시 코드에 전략/시간복잡도 주석
- [ ] 사고 질문 3개 이상

### 5.3 주간 회고 제출 전 체크
- [ ] 실수 패턴 표 2행 이상
- [ ] 이해 부족 개념 2개 이상
- [ ] 다음 주 개선 계획 3개 이상

---

## 6. Do & Don’t (운영 원칙)

**Do**
- 개념은 “왜 필요한가”로 시작한다 (문제 맥락 포함).
- 반례를 통해 적용 조건을 명확히 한다.
- 시간복잡도와 자료구조 선택 이유를 문서화한다.
- 파일명/섹션 순서를 항상 표준에 맞춘다.

**Don’t**
- 코드만 던지고 설명을 생략하지 않는다.
- 단순 암기형 요약으로 끝내지 않는다.
- 주간 회고를 건너뛰지 않는다 (누적 학습 품질 저하).

---

## 7. 버전 관리 규칙

- 커밋 메시지: `feat: [PhaseN] dayNN - <topic>` 또는 `docs: concept-<topic>`  
- 변경 로그: 각 파일 하단에 `Changelog:` 섹션(선택)  
- 큰 구조 변경 시 `doc/algorithm-curriculum-structure.md` 갱신

---

## 8. 용어 정리 (Glossary)

- **교재형(V3):** 인강 없이 문서만으로 이해 가능한 설명 방식(비유→정의→그림→질문).
- **All-in-One:** 한 문제의 풀이·비교·질문·실수를 **단일 파일**에 통합하는 형식.
- **파라매트릭 서치:** 이분 탐색을 “정답 공간”에 적용하여 결정 문제로 변환하는 기법.

---

## 9. 예시 매핑 (샘플)

```
phase2-data-structure/
├── concept-greedy.md              # ← concept-v3 기반
├── day10-greedy-1.md              # ← day-file-skeleton 기반
└── day15-weekly-review.md         # ← weekly-review 기반
```

---

## 10. 마지막 한 줄

> **Phase 0 → 손이 익히는 단계.**  
> **Phase 1~5 → 머리가 이해하는 단계.**  
> 표준 문서를 지키면, 어떤 새로운 방/프로젝트에서도 동일한 품질로 학습을 이어갈 수 있다.
