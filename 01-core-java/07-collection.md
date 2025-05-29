# 07. 컬렉션(Collection) 기초 (2025-05-21)

자바에서 가장 많이 사용하는 컬렉션 클래스인  
`ArrayList`, `HashMap`, `HashSet`의 기본 구조와 동작 방식을 실습합니다.

---

## 주요 개념

- `ArrayList`는 순서 유지 + 중복 허용
- `HashMap`은 키-값 쌍 저장, 키 중복 시 덮어쓰기
- `HashSet`은 순서 없음 + 중복 불허

---

## 예제 코드 1 – 기본 컬렉션 동작

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        // ArrayList 예시
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("사과"); // 중복 허용
        System.out.println("과일 리스트: " + fruits);
        System.out.println("첫번째 과일: " + fruits.get(0));

        // HashMap 예시
        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("봄이", 95);
        scores.put("온이", 100);
        scores.put("봄이", 90); // 키 중복 → 값 덮어쓰기
        System.out.println("점수표: " + scores);

        // HashSet 예시
        HashSet<String> colors = new HashSet<>();
        colors.add("빨강");
        colors.add("초록");
        colors.add("빨강"); // 중복 무시
        System.out.println("색상 목록: " + colors);
    }
}
```
---

## 실습 미션 1 – `ArrayList 중복 허용 확인`

```java
import java.util.ArrayList;

public class Lab1 {
    public static void main(String[] args) {
        ArrayList<String> seasons = new ArrayList<>();
        seasons.add("봄");
        seasons.add("여름");
        seasons.add("봄");
        System.out.println("계절 리스트: " + seasons);
    }
}
```
---

## 실습 미션 2 – `HashMap에 이름-나이 저장`

```java
import java.util.HashMap;

public class Lab2 {
    public static void main(String[] args) {
        HashMap<String, Integer> ages = new HashMap<>();
        ages.put("온이", 5);
        ages.put("봄이", 6);
        System.out.println("나이 정보:" + ages);
    }
}
```
---

## 실습 미션 3 – `HashSet의 중복 제거 확인`

```java
import java.util.HashSet;

public class Lab3 {
    public static void main(String[] args) {
        HashSet<String> codings = new HashSet<>();
        codings.add("JAVA");
        codings.add("JAVA");
        codings.add("Python");
        System.out.println("프로그래밍 언어: " + codings);
    }
}
```