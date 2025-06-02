# [6단계] 배열 (Array)

> 똑같은 종류의 데이터를 여러 개 저장하고 싶을 때,
> 
> 
> 변수를 하나씩 만들 필요 없이 **배열**로 한 번에 다루자!
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 | 예시 |
| --- | --- | --- |
| 배열 (Array) | 여러 개의 데이터를 하나의 변수에 저장하는 자료구조 | `int[] scores = {90, 80, 70};` |
| 인덱스 (Index) | 배열 요소에 접근할 때 쓰는 번호 (0부터 시작) | `scores[0] → 90` |
| 배열 길이 | 배열 안에 들어있는 데이터의 개수 | `scores.length` |
| 배열 선언 | 타입 + 대괄호 `[]` | `String[] names = new String[3];` |

---

### 🧾 예시 코드: 점수 배열 출력 & 평균 구하기

```java
public class ScoreArray {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 90};

        // 배열 요소 출력
        for (int i = 0; i < scores.length; i++) {
            System.out.println("점수 " + (i + 1) + ": " + scores[i]);
        }

        // 평균 계산
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        double average = (double) sum / scores.length;
        System.out.println("평균 점수: " + average);
    }
}
```

---

### 📌 포인트 요약

- 배열은 **고정 길이** 구조 → 생성 후에는 길이 변경 ❌
- `scores[0]` → 첫 번째 값
- `scores.length` → 배열의 길이
- `for-each` 문 → 더 간단하게 전체 순회할 수 있음

---

### 🧪 실습 미션

> ✅ 아래 배열을 사용해 직접 출력 & 평균 구하기 실습!
> 
1. `String[] books = {"자바", "파이썬", "자바스크립트"};`
    
    → `for`문으로 책 목록 출력
    
2. `int[] weights = {50, 65, 70};`
    
    → 평균 체중 구해보기 (형 변환 포함)
    

---

### ❔ 질문 1. `for (int score : scores)` 이건 뭐야?

> scores 배열 안에 있는 값을 하나씩 꺼내서
> 
> 
> `score` 변수에 담아 반복해줘!
> 

```java
int[] scores = {85, 92, 78};

for (int score : scores) {
    System.out.println(score);
}
```

📌 내부 동작:

1. `score = 85`
2. `score = 92`
3. `score = 78`

| 언제 쓰면 좋아? | 설명 |
| --- | --- |
| ✅ 순서대로 전체 탐색할 때 | `for (변수 : 배열)` |
| ❌ 인덱스가 필요한 경우 | 일반 `for`문 사용 추천 |

---

### ❔ 질문 2. `(double)`은 왜 쓰는 거야?

> 자바에서 정수끼리 나누면 정수!
> 
> 
> 소수점까지 정확히 계산하려면 하나를 **double**로 바꿔줘야 해!
> 

```java
int sum = 355;
int count = 4;

double average = (double) sum / count;  // 88.75
```

| 용어 | 설명 | 예시 |
| --- | --- | --- |
| 형 변환 (Casting) | 타입을 바꿔주는 것 | `(double) sum`, `(int) 3.14` |
| 명시적 변환 | 직접 지정 | `(double)`, `(int)` |
| 암묵적 변환 | 자바가 자동 | `int → double` 자동 OK |

---

> 한 줄 요약:
> 
> 
> `(double)`은 `"소수점까지 계산해줘!"`라는 뜻이야!
>