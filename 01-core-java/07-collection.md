# [7단계] 컬렉션 기초: ArrayList, HashMap, HashSet

> 데이터를 효율적으로 모으고 관리하는 자바의 대표 컬렉션 3총사!
> 
> 
> `ArrayList`, `HashMap`, `HashSet`의 특징과 사용법을 익혀보자!
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| `ArrayList` | 순서 O, 중복 O → 동적 배열 구조 |
| `HashMap` | 키-값 쌍으로 저장, 순서 X, 키 중복 X |
| `HashSet` | 중복 X, 순서 X → 고유한 값 저장 |
| `import` | `java.util.ArrayList`, `HashMap`, `HashSet` 필요 |

---

### 🧾 예시 코드

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

        // HashMap 예시
        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("봄이", 95);
        scores.put("온이", 100);
        scores.put("봄이", 80); // 키 중복 → 값 덮어쓰기
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

### 📌 포인트 요약

- `ArrayList`: 순서대로 저장됨, 인덱스로 접근 (`get(index)`)
- `HashMap`: 키 중복 불가 → `put()` 시 덮어쓰기 발생
- `HashSet`: 중복 불가, 순서 없음 → 고유값 보관용
- 반드시 `import java.util.클래스명;` 필요!

---

### 🧪 실습 미션

> ✅ 아래 조건을 만족하는 코드를 작성해보자!
> 
1. `ArrayList`에 `"봄"`, `"여름"`, `"봄"` 저장하고 출력
2. `HashMap`에 `"온이" → 5`, `"봄이" → 6` 저장
3. `HashSet`에 `"JAVA"`, `"JAVA"`, `"Python"` 저장하고 출력

✨ 실행 결과에서 **중복 처리 차이**를 꼭 확인해보자!

---

## 📚 컬렉션 실무 기능 요약

### ✅ 1. ArrayList

| 기능 | 코드 예시 |
| --- | --- |
| 값 삭제 | `list.remove(1);` 또는 `list.remove("값");` |
| 값 수정 | `list.set(0, "변경값");` |
| 정렬 | `Collections.sort(list);` / `Collections.reverse(list);` |
| 값 가져오기 | `list.get(index);` |

---

### ✅ 2. HashMap

| 기능 | 코드 예시 |
| --- | --- |
| 값 가져오기 | `map.get("키");` |
| 값 수정 | `map.put("키", 새로운값);` |
| 값 삭제 | `map.remove("키");` |

---

### ✅ 3. HashSet

| 기능 | 코드 예시 |
| --- | --- |
| 값 삭제 | `set.remove("값");` |
| 포함 여부 확인 | `set.contains("값"); // true or false` |

---

## 🧾 컬렉션 요약 비교표

| 컬렉션 | 값 삭제 | 값 수정 | 값 가져오기 | 순서 조정 |
| --- | --- | --- | --- | --- |
| ArrayList | `remove(index)` / `remove("값")` | `set(index, newValue)` | `get(index)` | `sort()`, `reverse()` |
| HashMap | `remove("키")` | `put("키", 값)` | `get("키")` | ❌ 없음 |
| HashSet | `remove("값")` | ❌ (삭제 후 추가 방식) | `contains("값")` | ❌ 없음 |