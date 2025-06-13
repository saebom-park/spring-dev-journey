import java.io.File;
import java.io.IOException;

public class CreateFileExample {
    public static void main(String[] args) {
        File file = new File("01-core-java/src/createFile.txt");    // 새 파일 경로 지정
        try {
            if (file.createNewFile()) { // 파일이 새로 만들어 졌는지 확인
                System.out.println("파일이 성공적으로 생성되었습니다.");
            } else {
                System.out.println("이미 같은 이름의 파일이 존재합니다.");
            }
        } catch (IOException e) {
            System.out.println("파일 생성 중 오류 발생: " + e.getMessage());
        }
    }

}