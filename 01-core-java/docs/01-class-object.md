# [1단계] 클래스 & 객체

> 자바의 시작은 클래스(class) 와 객체(object)
> 
> 
> 설계도(class)를 만들고, 그걸로 실제 객체를 생성해보자!
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| 클래스 | 객체의 설계도. 속성과 기능을 정의 |
| 객체 | 클래스로부터 생성된 실체. 메모리에 실제 존재 |
| 주요 키워드 | `class`, `new`, `.`, `void`, `static` 등 |

---

### 🧾 예시 코드

```java
public class Dog {
    String name;
    int age;

    void bark() {
        System.out.println("멍멍!");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.name = "초코";
        myDog.age = 3;
        myDog.bark();  // 출력: 멍멍!
    }
}
```

---

### 📌 포인트 요약

- `new`로 객체 생성 → `new Dog();`
- `.`으로 속성과 기능 사용 → `myDog.name`, `myDog.bark();`
- `void`는 return 없음 의미
- `static`은 클래스에 소속된 고정된 메서드나 변수

---

### 🧪 실습 미션

> ✅ Book 클래스를 만들고
> 
> 
> `title`, `author` 속성 + `showInfo()` 메서드 작성
> 
> `Main`에서 객체 생성 후 출력까지 해보자!
>