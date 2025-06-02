# [12단계] 인터페이스 (interface)

> "완전히 공통된 동작만 강제하고 싶을 땐?"
> 
> 
> 바로 **인터페이스(interface)의 세계**야!

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| `interface` | 클래스가 구현해야 할 **메서드의 틀만 정의** (몸통 ❌) |
| `implements` | 클래스가 인터페이스를 **구현할 때 사용하는 키워드** |
| 다형성 | 인터페이스 타입으로 다양한 객체를 담을 수 있음 |
| 다중 구현 | 한 클래스가 여러 인터페이스를 동시에 구현할 수 있음 |
| 추상 클래스와의 차이 | `abstract class`는 필드/일반 메서드 가짐 가능, `interface`는 메서드 선언만 존재 (Java 8+는 `default`, `static` 허용) |

---

### 🧾 예시 코드

```java
// 인터페이스 정의
interface Animal {
    void speak();
}

// 구현 클래스 1
class Dog implements Animal {
    public void speak() {
        System.out.println("멍멍!");
    }
}

// 구현 클래스 2
class Cat implements Animal {
    public void speak() {
        System.out.println("야옹~");
    }
}

// 실행
public class Main {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        Animal myCat = new Cat();

        myDog.speak(); // 멍멍!
        myCat.speak(); // 야옹~
    }
}
```

---

### 📌 포인트 요약

- `interface`는 **추상 메서드들의 모음**
- 인터페이스 안의 메서드는 자동으로 `public abstract`
- 클래스가 인터페이스를 구현할 땐 `implements` 사용
- **다형성**으로 `interface` 타입에 여러 객체 담을 수 있음
- 한 클래스는 **여러 인터페이스를 동시에 구현 가능**

---

### 🧪 실습 미션

> ✅ 다음 조건에 맞게 코드를 작성해보자!
> 
1. `interface Playable` 정의 → `void play()` 메서드 선언  
2. `Guitar`, `Piano` 클래스는 `Playable` 인터페이스를 구현  
3. `Main`에서 `Playable` 배열 생성 → 각 요소의 `play()` 호출  

✅ 출력 예시:

```
딩가딩가딩~가 딩가딩가딩~
따라란~ 따라란~
```

```java
interface Playable {
    void play();
}

class Guitar implements Playable {
    public void play() {
        System.out.println("딩가딩가딩~가 딩가딩가딩~");
    }
}

class Piano implements Playable {
    public void play() {
        System.out.println("따라란~ 따라란~");
    }
}

public class Main {
    public static void main(String[] args) {
        Playable[] playLists = { new Guitar(), new Piano() };

        for (Playable playList : playLists) {
            playList.play(); // 각각의 play() 실행
        }
    }
}
```

---

## ❔ 질문 정리

---

### ❓ interface랑 abstract class는 언제 쓰는 거야?

| 구분 | 인터페이스 (`interface`) | 추상 클래스 (`abstract class`) |
| --- | --- | --- |
| 목적 | **동작에 대한 규약(계약)** 정의 | **공통 속성과 동작의 틀** 제공 |
| 다중 상속 | ✅ 가능 (여러 개 구현 가능) | ❌ 불가능 |
| 구성 요소 | `abstract method` (Java8+부터 default 메서드 허용) | 필드, 생성자, 일반 메서드, 추상 메서드 모두 가능 |
| 사용 예시 | `Comparable`, `Runnable`, `Serializable` | `Animal`, `Shape` 등 공통 속성 있는 클래스 |

> 공통 코드 재사용이 필요하면 **abstract class**,  
> 공통된 행위만 강제하고 싶다면 **interface**를 선택해!