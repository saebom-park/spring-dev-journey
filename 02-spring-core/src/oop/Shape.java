// * 실습 미션: 다형성 + 업/다운캐스팅 구조 직접 구현해보기
// 1. `Shape` 클래스: `draw()` 메서드 → `"도형을 그립니다."`
// 2. `Circle`, `Rectangle` 클래스: `draw()` 오버라이딩
// 3. 각 클래스에만 존재하는 고유 메서드 추가
//    - `Circle`: `radiusInfo()` → `"반지름은 5cm 입니다."`
//    - `Rectangle`: `angleInfo()` → `"직각을 이룹니다."`
// 4. `Shape[]` 배열에 다양한 자식 객체들을 담은 뒤,
// 5. 반복문에서 `draw()` 호출 → `instanceof`로 다운캐스팅 후 고유 메서드 호출

package oop;

public class Shape {
    public void draw() {
        System.out.println("도형을 그립니다.");
    }
}