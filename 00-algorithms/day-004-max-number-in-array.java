// Day4. 최댓값과 최솟값
// - 문제 설명
// 문자열 s에는 공백으로 구분된 숫자들이 저장되어 있습니다. str에 나타나는 숫자 중 최소값과 최대값을 찾아 이를 "(최소값) (최대값)"형태의 문자열을 반환하는 함수, solution을 완성하세요.
// 예를들어 s가 "1 2 3 4"라면 "1 4"를 리턴하고, "-1 -2 -3 -4"라면 "-4 -1"을 리턴하면 됩니다.
//
// - 제한 조건
// s에는 둘 이상의 정수가 공백으로 구분되어 있습니다.


// [내 풀이]
public class Solution {
    public String solution(String str) {
        String[] arr = str.split(" ");

        String min = arr[0];
        String max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (Integer.parseInt(arr[i]) < Integer.parseInt(min)) {
                min = arr[i];
            }
            if (Integer.parseInt(arr[i]) > Integer.parseInt(max)) {
                max = arr[i];
            }
        }

        return min + " " + max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution("-1 1 3 -4"));
    }
}


// [내 풀이 개선]
public class Solution {
    public String solution(String str) {
        String[] arr = str.split(" ");

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (String a : arr) {
            int num = Integer.parseInt(a);
            
            if (num < min) min = num;
            if (num > max) max = num;
        }

        return min + " " + max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution("1 2 9 11 -23 -21 -7"));
    }
}


// 실수 노트
// 1. min, max를 String 타입으로 두고 Integer.parseInt()를 반복 호출 → 비효율적
//    → int 타입으로 두고 한 번만 파싱해서 비교하는 게 더 적절함
// 2. for문 안에서 Integer.parseInt()를 if문마다 두 번 호출 → 중복 연산 발생
//    → 한 번 파싱한 값을 변수에 담아 비교해야 함
// - 교훈: 숫자 비교는 문자열이 아니라 int로 직접 다루는 습관을 들이자.


// [온이 풀이 - 기본 스타일]
public class OniSolution {
    public String solution(String s) {
        String[] arr = s.split(" ");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (String numStr : arr) {
            int num = Integer.parseInt(numStr);
            if (num < min) min = num;
            if (num > max) max = num;
        }

        return min + " " + max;
    }

    public static void main(String[] args) {
        OniSolution sol = new OniSolution();
        System.out.println(sol.solution("1 2 3 4"));      // "1 4"
        System.out.println(sol.solution("-1 -2 -3 -4")); // "-4 -1"
        System.out.println(sol.solution("3 3 3"));       // "3 3"
        System.out.println(sol.solution("-1 1 3 -4"));   // "-4 3"
    }
}


// [온이 풀이 - Stream 스타일]
import java.util.Arrays;

public class OniStreamSolution {
    public String solution(String s) {
        int[] nums = Arrays.stream(s.split(" "))
                           .mapToInt(Integer::parseInt)
                           .toArray();
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();
        return min + " " + max;
    }

    public static void main(String[] args) {
        OniStreamSolution sol = new OniStreamSolution();
        System.out.println(sol.solution("1 2 3 4"));      // "1 4"
        System.out.println(sol.solution("-1 -2 -3 -4")); // "-4 -1"
        System.out.println(sol.solution("3 3 3"));       // "3 3"
        System.out.println(sol.solution("-1 1 3 -4"));   // "-4 3"
    }
}
