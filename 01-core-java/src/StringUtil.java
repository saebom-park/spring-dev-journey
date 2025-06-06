// * 실습 미션: String 유틸 메서드 만들기
// 1. StringUtil 클래스 만들기
//  - static 메서드 isEmpty(String s)
//   → null이거나 빈 문자열이면 true 반환
//  - static 메서드 reverse(String s)
//   → 문자열 뒤집어서 반환
//  - static 메서드 toUpperCase(String s)
//   → 문자열을 대문자로 바꿔서 반환
// 2. StringUtilMain 클래스에서
//  - 위 3개의 static 메서드 직접 호출해서 결과 출력하기

import java.util.Objects;

public class StringUtil {
    public static boolean isEmpty(String s) {
        return s == null || Objects.equals(s, "");  // if문 단순화
    }

    public static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static String toUpperCase(String s) {
        return s.toUpperCase();
    }

    public static String toLowerCase(String s) {
        return s.toLowerCase();
    }


}