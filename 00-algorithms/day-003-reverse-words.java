// Day3. 단어 뒤집기
// - 문제 설명
//  : 문장이 주어졌을 때, 단어를 모두 뒤집어서 출력하는 프로그램을 작성하시오.
//    단, 단어의 순서는 바꿀 수 없다. 단어는 영어 알파벳으로만 이루어져 있다.
//    예를들어 N = 123이면 1 + 2 + 3 = 6을 return 하면 됩니다.
// - 출력
//  : 각 테스트 케이스에 대해서, 입력으로 주어진 문장의 단어를 모두 뒤집어 출력한다.


// [내 풀이]
import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();
        scanner.nextLine();

        for (int t = 0; t < T; t++) {
            String sentence = scanner.nextLine();
            String[] words = sentence.split(" ");
            StringBuilder result = new StringBuilder();

            for (String word : words) {
                result.append(new StringBuilder(word).reverse());
                result.append(" ");
            }

            System.out.println(result.toString().trim());
        }
    }
}


// 실수 노트
// 1. for문 변수명 혼동 (i ↔ t)
// 2. String에는 reverse() 없음 → new StringBuilder(word).reverse() 사용
// 3. 마지막 단어 뒤 공백 제거 필요 → trim()으로 해결
// 교훈: 백준 제출형에서는 입력/출력 구조를 항상 문제 요구사항에 맞게 구현해야 한다.


// [온이 풀이]
import java.util.Scanner;
class OniMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        sc.nextLine();

        while (T-- > 0) {
            String[] words = sc.nextLine().split(" ");
            StringBuilder sb = new StringBuilder();

            for (String w : words) {
                sb.append(new StringBuilder(w).reverse());
                sb.append(" ");
            }

            System.out.println(sb.toString().trim());
        }
        sc.close();
    }
}