// * 실습 미션: static과 인스턴트 변수의 차이를 직접 확인해보기
// 1. `Person` 클래스를 만들자
//    - `name`: 인스턴스 변수
//    - `population`: static 변수 (전 인구 수 추적)
// 2. 생성자에서 `population++` 하고, `name`은 생성자 파라미터로 전달
// 3. 3명의 Person을 만들고 각각 이름과 population을 출력해보자

public class Person {
    String name;
    static int population;

    Person(String name) {
        System.out.println("이름: " + name);
        population++;
    }

    public static void main(String[] args) {
        Person p1 = new Person("봄이");
        Person p2 = new Person("온이");
        Person p3 = new Person("지피티");

        System.out.println("population: " + Person.population + "명");
    }
}
