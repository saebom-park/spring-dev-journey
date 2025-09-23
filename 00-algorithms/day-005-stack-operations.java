// Day5. 스택
// - 문제 설명
//  : 정수를 저장하는 스택을 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.
//    명령은 총 다섯 가지이다.
//    * push X: 정수 X를 스택에 넣는 연산이다.
//    * pop: 스택에서 가장 위에 있는 정수를 빼고, 그 수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
//    * size: 스택에 들어있는 정수의 개수를 출력한다.
//    * empty: 스택이 비어있으면 1, 아니면 0을 출력한다.
//    * top: 스택의 가장 위에 있는 정수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
// - 입력
//  : 첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다. 둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다. 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다. 문제에 나와있지 않은 명령이 주어지는 경우는 없다.

// - 출력
//  : 출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력한다.


// [내 풀이]
import java.util.Scanner;
import java.util.Stack;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();
        scanner.nextLine();

        Stack<Integer> stack = new Stack<>();

        for (int t = 0; t < T; t++) {
            String[] command = scanner.nextLine().split(" ");
            
            switch (command[0]) {
                case "push":
                    stack.push(Integer.parseInt(command[1]));
                    break;
                case "pop":
                    System.out.println(stack.isEmpty() ? -1 : stack.pop());
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
                case "empty":
                    System.out.println(stack.isEmpty() ? 1 : 0);
                    break;
                case "top":
                    System.out.println(stack.isEmpty() ? -1 : stack.peek());
                    break;
            }

        }

        scanner.close();
    }
}

// [내 풀이 개선]
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            String[] cmd = br.readLine().split(" ");

            switch (cmd[0]) {
                case "push":
                    stack.push(Integer.parseInt(cmd[1]));
                    break;
                case "pop":
                    sb.append(stack.isEmpty() ? -1 : stack.pop()).append("\n");
                    break;
                case "size":
                    sb.append(stack.size()).append("\n");
                    break;
                case "empty":
                    sb.append(stack.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "top":
                    sb.append(stack.isEmpty() ? -1 : stack.peek()).append("\n");
                    break;
            }
        }

        System.out.print(sb);
    }
}


// 실수 노트
// 1. Scanner + System.out.println 조합 사용 → 입력/출력 데이터가 많을 경우 시간 초과 발생
//    → BufferedReader + StringBuilder 로 교체해야 함
//
// 2. split() 사용 시 구분자 생략 → 런타임 오류 발생
//    → 반드시 .split(" ") 으로 공백 지정 필요
//
// 3. stack.push(command[1]) 로 문자열 그대로 푸시 시도
//    → 제네릭 타입 불일치 (Integer 스택에 String 넣음)
//    → Integer.parseInt(command[1]) 로 변환 필요
//
// 4. StringBuilder 출력 시 println(sb) 사용
//    → 마지막에 불필요한 개행이 추가됨
//    → System.out.print(sb) 로 출력해야 정답
//
// - 교훈: 백준 대량 입출력 문제는 무조건 BufferedReader + StringBuilder 로 구현하고,
//         입력 split 시 구분자 주의, 자료형 변환을 철저히 해야 한다.



// [온이 풀이]
import java.io.*;
import java.util.*;

public class OniMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            String[] cmd = br.readLine().split(" ");

            switch (cmd[0]) {
                case "push":
                    stack.push(Integer.parseInt(cmd[1]));
                    break;
                case "pop":
                    sb.append(stack.isEmpty() ? -1 : stack.pop()).append("\n");
                    break;
                case "size":
                    sb.append(stack.size()).append("\n");
                    break;
                case "empty":
                    sb.append(stack.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "top":
                    sb.append(stack.isEmpty() ? -1 : stack.peek()).append("\n");
                    break;
            }
        }

        System.out.print(sb);
    }
}