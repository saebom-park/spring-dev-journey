# [2단계] 생성자 & this

> 객체가 생성될 때 자동으로 실행되는 특별한 메서드,
> 
> 
> 바로 **생성자(Constructor)**!
> 
> `this` 키워드는 객체 자기 자신을 가리킬 때 사용해요.
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| 생성자 | 객체가 생성될 때 자동으로 실행되는 메서드 |
| 기본 생성자 | 매개변수 없는 생성자 (`클래스명() {}`) |
| 매개변수 생성자 | 값을 전달받아 필드를 초기화하는 생성자 |
| this | 객체 자기 자신을 참조하는 키워드 |
| this.name = name | 지역변수와 필드명이 같을 때 구분하는 용도 |

---

### 🧾 예시 코드

```java
public class Dog {
    String name;
    int age;

    // 생성자
    Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void introduce() {
        System.out.println("이름: " + name + ", 나이: " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog1 = new Dog("아부", 7);
        dog1.introduce();  // 출력: 이름: 아부, 나이: 7
    }
}
```

---

### 📌 포인트 요약

- 생성자는 클래스와 **이름이 같고**, **리턴 타입이 없음**
- 객체가 만들어질 때 **자동으로 실행됨**
- `this.변수`는 **자기 자신의 필드를 가리킴**
- **매개변수 이름과 필드 이름이 겹칠 때 필수 사용!**
- 생성자는 여러 개 만들 수 있음 → **오버로딩**

---

### 🧪 실습 미션

> ✅ Book 클래스를 만들고
> 
> 
> `title`, `author`를 초기화하는 **생성자** 작성
> 
> `showInfo()` 메서드로 책 정보 출력
> 

```java
Book b = new Book("불변의 법칙", "봄이잇");
b.showInfo();  // 출력: 제목: 불변의 법칙, 저자: 봄이잇
```