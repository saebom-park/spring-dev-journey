# [8단계] 반복문 기초: for, while, 향상된 for문

> 프로그램을 자동으로 반복시키는 제어 흐름 구조!
> 
> 
> 다양한 반복문을 익혀서 상황에 맞는 반복 제어를 배워보자 💫
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| `for`문 | 반복 횟수가 정해졌을 때 사용 (`초기값 → 조건 → 증감`) |
| `while`문 | 조건이 참일 동안 반복 (`조건 확인 → 실행`) |
| `do-while`문 | **무조건 한 번 실행** 후 조건 확인 |
| 향상된 `for`문 | 배열이나 컬렉션 순회에 최적화 (`for-each`) |

---

### 🧾 예시 코드

```java
public class LoopDemo {
    public static void main(String[] args) {
        // 기본 for문
        for (int i = 0; i < 3; i++) {
            System.out.println("for 반복: " + i);
        }

        // while문
        int j = 0;
        while (j < 3) {
            System.out.println("while 반복: " + j);
            j++;
        }

        // do-while문
        int k = 0;
        do {
            System.out.println("do-while 반복: " + k);
            k++;
        } while (k < 3);

        // 향상된 for문 (for-each)
        String[] colors = {"빨강", "초록", "파랑"};
        for (String color : colors) {
            System.out.println("색상: " + color);
        }
    }
}
```

---

### 📌 포인트 요약

- `for`: **정해진 횟수 반복**에 적합
    
    → `for (int i = 0; i < n; i++)`
    
- `while`: 조건만 검사 → 조건이 거짓이면 한 번도 실행 안 됨
- `do-while`: **조건 상관없이 무조건 1회 실행**
- 향상된 `for`: `for (타입 변수 : 배열)` → 배열/리스트 순회에 편리

---

### 🧪 실습 미션

> ✅ 아래 조건에 맞는 반복문을 작성해보자!
> 
1. `for`문으로 1~5까지 출력
2. `while`문으로 `"Hello 봄이!"`를 3번 출력
3. `do-while`문으로 `"최소 1번은 실행"`을 1회 출력
4. `String[] subjects = {"Java", "Spring", "SQL"};`
    
    → 향상된 `for`문으로 과목명 전부 출력하기!