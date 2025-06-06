// * 실습 미션: static 메서드의 특성을 체험해보기
// 1. `Converter` 클래스를 만들자
//    - static 메서드 `cmToMm(int cm)` → cm를 mm로 변환
//    - static 메서드 `kgToG(int kg)` → kg를 g으로 변환
// 2. `ConverterMain`에서 static 메서드를 객체 없이 호출하고 출력하자

public class Converter {
    public static int cmToMm(int cm) {
        return cm * 10;
    }

    public static int kgToG(int kg) {
        return kg * 1000;
    }
}