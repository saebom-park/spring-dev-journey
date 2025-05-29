# 08. 반복문 기초 (2025-05-29)

자바의 반복문 구조를 익히고,  
기초 문법부터 실전 미션까지 다양한 반복문 패턴을 실습합니다.

---

## 주요 개념

- `for`, `while`, `do-while` 기본 문법
- `for-each` 문법을 활용한 배열 순회
- 반복문을 활용한 누적합, 평균 계산, 조건 분기 등 실전 문제 응용

---

## 예제 코드 – 기본 반복문 구조

```java
public class LoopDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            System.out.println("for 반복: " + i);
        }

        int j = 0;
        while (j < 3) {
            System.out.println("while 반복: " + j);
            j++;
        }

        int k = 0;
        do {
            System.out.println("do-while 반복: " + k);
            k++;
        } while (k < 3);

        String[] colors = {"빨강", "초록", "파랑"};
        for (String color : colors) {
            System.out.println("색상: " + color);
        }
    }
}
```
---

## 실습 미션 1 – `1부터 5까지 for문 출력`

```java
public class Lab1 {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println("숫자 출력: " + (i+1));
        }

        for (int i = 1; i <= 5; i++) {
            System.out.println("숫자 출력: " + i);
        }
    }
}
```
---

## 실습 미션 2 – `while문으로 3번 인사하기`

```java
public class Lab2 {
    public static void main(String[] args) {
        int i = 0;
        while (i < 3) {
            System.out.println("Hello 사랑스러운 봄이!");
            i++;
        }
    }
}
```
---

## 실습 미션 3 – `do-while문 반복 구조 확인`

```java
public class Lab3 {
    public static void main(String[] args) {
        int i = 0;
        int rst = 0;
        do {
            rst = 2 * (i+1);
            System.out.println("2 x " + (i+1) + " = " + rst);
            i++;
        } while (i < 9);
    }
}
```
---

## 실습 미션 4 – `for-each 문으로 배열 출력`

```java
public class Lab4 {
    public static void main(String[] args) {
        String[] codings = {"Java", "Spring", "SQL"};
        System.out.println("내가 좋아하는 프로그래밍 언어는?");
        for (String coding : codings) {
            System.out.println(coding);
        }
    }
}
```
---

## 추가 실습 미션 1 – `총점 & 평균 계산기`

```java
public class addLab1 {
    public static void main(String[] args) {
        int sum = 0;
        int averageI = 0;
        double averageD = 0;
        int[] scores = {90, 80, 100, 75, 95};

        for (int i = 0; i < scores.length; i++) {
            System.out.println("점수" + (i+1) + ": " + scores[i] + "점");
            sum += scores[i];
        }

        averageI = sum / scores.length;
        averageD = (double) sum / scores.length;

        System.out.println("총점: " + sum + "점");
        System.out.println("평균(정수): " + averageI + "점");
        System.out.println("평균(실수): " + averageD + "점");
    }
}
```
---

## 추가 실습 미션 2 – `최고점 & 최저점 찾기`

```java
import java.util.ArrayList;

public class addLab2 {
    public static void main(String[] args) {
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(88);
        scores.add(92);
        scores.add(79);
        scores.add(10);
        scores.add(67);
        scores.add(85);

        int maxScore = scores.get(0);
        int minScore = scores.get(0);

        System.out.println("모든 점수:");
        System.out.println("-----------------");

        for (int score : scores) {
            if (maxScore < score) maxScore = score;
            if (minScore > score) minScore = score;
            System.out.println(score);
        }

        System.out.println("-----------------");
        System.out.println("최고점: " + maxScore + "점");
        System.out.println("최저점: " + minScore + "점");
    }
}
```