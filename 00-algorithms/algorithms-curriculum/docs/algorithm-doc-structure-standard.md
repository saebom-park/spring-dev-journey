# 📚 Algorithm Doc Structure Standard
### (Phase별 템플릿 적용 기준)

> 위치: `00-algorithms/doc/algorithm-doc-structure-standard.md`  
> 목적: 모든 Phase에서 **어떤 문서를, 언제, 어떤 템플릿으로** 작성하는지 표준화한다.

---

## 0. 범위 & 요약

- **Phase 0 = 문법 체화 단계** → 문제 파일만 작성 (템플릿: `day-file-skeleton.md`)
- **Phase 1~5 = 개념+사고력 단계** → 개념 문서 + 문제 파일
  - 개념 문서 템플릿: `templates/concept-v3.md`
  - 문제 파일 템플릿: `templates/day-file-skeleton.md`

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
└── docs/
    ├── algorithm-curriculum-structure.md        # 전체 구조 정리
    ├── algorithm-curriculum-master-schedule.md  # 전체 진도표
    └── templates/
        ├── concept-v3.md
        └── day-file-skeleton.md
```

---

## 2. 파일 네이밍 & 메타 규칙

- **개념 문서:** `concept-<topic>.md` (예: `concept-greedy.md`, `concept-bfs.md`)
- **문제 파일:** `dayNN-<topic>.md` (예: `day08-prefix-basic.md`)
- **README:** 각 Phase 루트에 진행 현황 표 포함
- **메타 블록(문제 파일 상단):**
  ```markdown
  > 📆 Date: YYYY-MM-DD  
  > ⏱ 설계/구현/디버그: 10m/20m/5m  
  > 💬 난이도: Lv1  
  > 🧭 Phase: N
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

### 4.2 개념 문서(교재형) 작성 규칙
- 4단 구성: **인트로(비유) → 정의/조건 → 시각 흐름 → 사고 질문**
- 예시 코드 5~15줄 내외, **전략/시간복잡도** 명시
- 반례/적용 조건을 **명확히 표기** (그리디/파라매트릭 등)

### 4.3 문제 파일 작성 규칙
- All-in-One 섹션 고정: **💡→🧾→📌→🌱→📒→🐞**
- **온이 풀이**에는 반드시 전략 요약 + 시간복잡도 포함
- **비교 포인트**는 표로 구체화 (정렬 기준/자료구조/업데이트 타이밍 등)