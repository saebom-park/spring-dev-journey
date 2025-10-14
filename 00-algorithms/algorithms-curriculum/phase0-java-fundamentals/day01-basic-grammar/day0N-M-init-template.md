# 🧩 Day01-1 — 짝수와 홀수 (Even or Odd)

> 📆 Date: 2025-10-15  
> ⏱ 설계 / 구현 / 디버그: — / — / —  
> 💬 난이도: Lv1  
> 🧭 Phase: 0  
> 🔁 복습일: D+3  

---

## 🧩 문제 설명
정수 `n`이 주어졌을 때,  
짝수라면 `"Even"`, 홀수라면 `"Odd"`를 출력하는 프로그램을 작성하시오.  

**입력 예시**
```
7
```

**출력 예시**
```
Odd
```

---

## 💡 핵심 개념 요약
- `%` 연산자: **나머지**를 구할 때 사용.  
  → `n % 2 == 0` 이면 짝수, 그렇지 않으면 홀수.  
- `if / else`: **조건에 따라 분기하는 제어문.**  
- `Scanner`: 표준 입력을 처리하는 가장 기본적인 방법.  
- 삼항 연산자 `? :` 를 사용하면 한 줄 표현도 가능.  
- 알고리즘에서의 조건문은 단순 분기가 아니라 **사고 구조의 기초.**  
  → 나중에 `DFS`, `DP`, `Greedy` 등에서도 조건 판별이 핵심 역할을 함.  

---

## 🧾 문제 풀이

### ✏️ 내 풀이
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 새봄이 직접 작성할 부분
    }
}
```

### 💎 온이 풀이
```java
import java.util.Scanner;

public class MainOni {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 온이는 새봄 코드 작성 후 비교/해설용으로 채움
    }
}
// ✅ 전략: 조건문을 한 줄 삼항 연산자로 단축 (추후 작성)
// ⏱ 시간복잡도: O(1)
```
