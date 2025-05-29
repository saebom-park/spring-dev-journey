# 06. 배열 기초와 응용 (2025-05-20)

배열 선언, 반복문을 통한 순회, 평균 계산 등  
자바 배열의 기초 사용법과 간단한 응용 미션을 실습합니다.

---

## 주요 개념

- 배열 선언 및 초기화 (`int[]`, `String[]`)
- `length` 속성을 이용한 반복 처리
- `for`, `for-each` 반복문 사용
- 배열을 활용한 실전 미션 문제 풀이

---

## 예제 코드 1 – `ScoreArray`

```java
public class ScoreArray {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 90};

        for (int i = 0; i < scores.length; i++) {
            System.out.println("점수 " + (i+1) + ": " + scores[i]);
        }

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

## 예제 코드 2 – `책 목록 출력 (실습 미션 1)`

```java
public class bookArray {
    public static void main(String[] args) {
        String[] books = {"JAVA 완전 정복", "Python과 친해지기", "실무자를 위한 ASP 가이드"};

        for (int i = 0; i < books.length; i++) {
            System.out.println("내가 좋아하는 책 순위 " + (i+1) + "위는? " + books[i]);
        }
    }
}
```
---

## 예제 코드 3 – `평균 체중 계산 (실습 미션 2)`

```java
public class weightArray {
    public static void main(String[] args) {
        int[] weekMyWeights = {54, 51, 55, 52, 53, 57, 54};

        int sum = 0;
        for (int myWeight : weekMyWeights) {
            sum += myWeight;
        }

        double averageMyWeight = (double) sum / weekMyWeights.length;

        if (averageMyWeight > 50) {
            System.out.println("나의 주간 평균 몸무게는: " + averageMyWeight + "kg 이고 다이어트가 시급한 상태예요.");
        } else {
            System.out.println("나의 주간 평균 몸무게는: " + averageMyWeight + "kg 이고 잘 유지하면 될 것 같아요.");
        }
    }
}
```