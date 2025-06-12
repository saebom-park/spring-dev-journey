import java.io.File;
import java.util.Scanner;

public class FilePractice {
    public static void main(String[] args) {
        System.out.println("[파일 정보 확인 시스템]");
        Scanner scanner = new Scanner(System.in);
        System.out.print("파일 전체 경로를 입력해 주세요: ");
        String path = scanner.nextLine();
        
        File file = new File(path);

        if (file.exists()) {
            System.out.println("파일이 존재합니다.");
            System.out.println("절대 경로: " + file.getAbsolutePath());
            System.out.println("파일 크기: " + file.length() + "bytes");

            if (file.isFile()) {
                System.out.println("이것은 파일입니다.");
            } else if (file.isDirectory()) {
                System.out.println("이것은 폴더입니다.");
            }
        } else {
            System.out.println("파일이 존재하지 않습니다.");
        }
        
        // 디버깅 용도
        System.out.println("현재 실행 중인 디렉토리: " + System.getProperty("user.dir"));
    }
}