# 🧩 Day01-3 — while 반복문으로 1부터 N까지의 합 (Sum to N using while)

> 📆 Date: 2025-10-15  
> ⏱ 설계 / 구현 / 디버그: — / — / —  
> 💬 난이도: Lv1  
> 🧭 Phase: 0  
> 🔁 복습일: D+3  

---

## 🧩 문제 설명
정수 `n`이 주어졌을 때,  
`while` 문을 이용하여 1부터 `n`까지의 합을 구하는 프로그램을 작성하시오.  

**입력 예시**
```
5
```

**출력 예시**
```
15
```

---

## 💡 핵심 개념 요약
- `while` 문은 **조건이 참인 동안 반복**되는 제어문.  
  → 반복 횟수가 정해지지 않았거나, 조건에 따라 종료될 때 사용.  
- `for` 문은 **초기화, 조건, 증감**이 한 줄에 있지만  
  `while`은 **분리**되어 있어 직접 변수 증가를 관리해야 함.  
- 기본 구조:
  ```java
  int i = 1;
  while (i <= n) {
      sum += i;
      i++;
  }
  ```
- 초기화(`i=1`), 조건(`i<=n`), 증감(`i++`) 세 가지를 모두 신경 써야 함.  
- 종료 조건을 잘못 설정하면 **무한 루프** 발생 가능.  

---

## 🧾 문제 풀이

### ✏️ 내 풀이
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int sum = 0;

        while (int i <= n) {
          sum += i;
        }

        System.out.println(sum);
        
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
// ✅ 전략: while문을 이용해 i를 직접 증가시키며 누적합 계산
// ⏱ 시간복잡도: O(n)
```