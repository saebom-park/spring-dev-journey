# 09. 클래스 간 관계: 포함 관계 (Has-A) (2025-05-27)

**포함 관계(Has-A 관계)**란, 한 클래스가 다른 클래스를 **필드로 포함**하는 관계입니다.  
실제 객체 설계에서 매우 자주 사용되는 구조입니다.

---

## 주요 개념

- 포함(Has-A) 관계는 "가지고 있다"는 의미  
  (예: Member has an Address, Employee has a Company)
- 포함된 객체의 메서드나 필드를 **`.` 연산자**로 접근
- 보통 포함된 클래스는 별도 파일 or 내부 클래스 형태로 정의
- `public` 제한자는 필요한 경우에만 사용 (실행용 메인 클래스만 public 권장)

---

## 예제 코드 1 – `Member`와 `Address` 포함 관계

```java
// 주소 클래스
class Address {
    String city;
    String street;

    void showAddress() {
        System.out.println(city + " " + street);
    }
}

// 회원 클래스
class Member {
    String name;
    int age;
    Address address; // 포함 관계 (has-a)

    void showInfo() {
        System.out.println("이름: " + name);
        System.out.println("나이: " + age);
        System.out.println("주소: ");
        address.showAddress();
    }
}

// 메인 클래스
public class Main {
    public static void main(String[] args) {
        Address addr = new Address();
        addr.city = "수원시";
        addr.street = "효원로266번길";

        Member member = new Member();
        member.name = "봄이";
        member.age = 28;
        member.address = addr;

        member.showInfo();
    }
}
```
---

## 실습 미션 – `Employee와 Company 포함 관계`

```java
class Company {
    String companyName;
    String location;

    public void showCompany() {
        System.out.println(companyName + " 위치: " + location);
    }
}

class Employee {
    String empName;
    Company company;

    public void showEmployee() {
        System.out.println("사원명: " + empName);
        System.out.println("회사명: ");
        company.showCompany();
    }
}

public class Main {
    public static void main(String[] args) {
        Company company1 = new Company();
        company1.companyName = "(주)메가스터디 교육";
        company1.location = "서울 특별시 서초구 효령로 321";

        Employee employee1 = new Employee();
        employee1.empName = "봄이";
        employee1.company = company1;

        employee1.showEmployee();
    }
}
```