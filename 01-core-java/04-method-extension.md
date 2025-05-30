# [4단계] return & 조건문

> 메서드가 값을 반환할 때는 return,
> 
> 
> 흐름을 제어할 때는 `if`, `else` 같은 **조건문**을 사용해요!
> 
> 로직을 만들기 위한 핵심 문법이에요 🔍
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| `return` | 메서드 실행 결과를 호출한 쪽으로 반환 |
| 반환 타입 | 메서드 앞에 명시 (`int`, `String`, `boolean` 등) |
| 조건문 | `if`, `else if`, `else`로 조건 분기 |
| 비교 연산자 | `==`, `!=`, `>`, `<`, `>=`, `<=` |
| boolean | true/false 값을 판단하는 자료형 |

---

### 🧾 예시 코드

```java
public class Dog {
    String name;
    int age;

    Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    int getBarkScore() {
        if (age < 5) {
            return 10;
        } else if (age <= 8) {
            return 7;
        } else {
            return 5;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Dog aboo = new Dog("아부", 7);
        int score = aboo.getBarkScore();
        System.out.println("아부의 짖음 점수: " + score);
    }
}
```

---

### 📌 포인트 요약

- `return`은 메서드를 **즉시 종료하고 값을 반환**
- `void` → 반환값 없음, 그 외 타입은 반드시 return 필요!
- 조건문은 **조건에 따라 분기하는 핵심 로직 제어 도구**
- `else if`로 **세분화**, `else`는 **기본/default 분기**

---

### 🧪 실습 미션

> ✅ Book 클래스에 getRating() 메서드 추가
> 
> 
> 나이에 따라 **추천 점수(1~10)** 를 반환해보자!
> 

```java
int getRating(int age) {
    if (age < 10) return 3;
    else if (age < 20) return 7;
    else return 9;
}
```