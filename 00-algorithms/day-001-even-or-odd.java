// Day1. 짝수와 홀수
// - 문제 설명
//  : 정수 num이 짝수일 경우 "Even"을 반환하고 홀수인 경우 "Odd"를 반환하는 함수, solution을 완성해주세요.
// - 제한 조건
//  : num은 int 범위의 정수입니다.
//  : 0은 짝수입니다.


// [내 풀이]
import java.util.Scanner;
class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();

        if (num % 2 == 0) {
            System.out.println("Even");
        } else {
            System.out.println("Odd");
        }

        scanner.close();
    }
}


// 실수 노트
// 1. main() 메서드는 return 으로 문자열 직접 반환 불가 → System.out.println 사용
// 2. 마지막에 스캐너 닫기


// [온이 풀이]
class OniSolution {
    public String solution(int num) {
        if (num % 2 == 0) {
            return "Even";
        } else {
            return "Odd";
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(3));
        System.out.println(solution(4));
        System.out.println(solution(5));
    }

}