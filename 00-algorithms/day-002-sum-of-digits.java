// Day2. 자릿수 더하기
// - 문제 설명
//  : 자연수 N이 주어지면, N의 각 자릿수의 합을 구해서 return 하는 solution 함수를 만들어 주세요.
//    예를들어 N = 123이면 1 + 2 + 3 = 6을 return 하면 됩니다.
// - 제한 조건
//  : N의 범위 : 100,000,000 이하의 자연수

// [내 풀이]
class SolutionMy {
    public int solution(int num) {
        String numStr = String.valueOf(Math.abs(num));
        int sum = 0;

        for (int i = 0; i < numStr.length(); i++) {
            sum += numStr.charAt(i) - '0';
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new SolutionMy().solution(146));
    }
}

// 실수 노트
// 1. int → String 변환 시 (String) 캐스팅 시도 → String.valueOf(num) 사용해야 함
// 2. 문자열 길이 → length() 메서드 써야 함
// 3. 문자열 인덱스 접근 → charAt(i) + '0' 빼서 숫자 변환
// 4. static 메서드가 아닌 경우 → 객체 생성 후 호출해야 함 (new SolutionMy().solution())
// 5. System.out.prinln 오타 → println

// [온이 풀이]
class Solution {
    public int solution(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(146)); // 11
        System.out.println(s.solution(123)); // 6
    }
}