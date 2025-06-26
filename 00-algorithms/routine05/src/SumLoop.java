import java.util.Scanner;

public class SumLoop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int cnt = scanner.nextInt();
        scanner.nextLine();
        String[] numberSet = new String[cnt];
        
        for (int i = 0; i < numberSet.length; i++) {
            numberSet[i] = scanner.nextLine();
        }
        
        for (int i = 0; i < numberSet.length; i++) {
            String[] parts = numberSet[i].split(" ");
            System.out.println(Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]));
        }     
    }  
}
