import java.io.File;

public class FileExample {
    public static void main(String[] args) {
        File file = new File("test.txt");

        if (file.exists()) {
            System.out.println("파일이 존재합니다.");
            System.out.println("절대 경로: " + file.getAbsolutePath());
            System.out.println("파일 크키: " + file.length() + "bytes");

            if (file.isFile()) {
                System.out.println("이건 파일입니다.");
            } else if (file.isDirectory()) {
                System.out.println("이건 폴더입니다.");
            }
        } else {
            System.out.println("파일이 존재하지 않습니다.");
        }
    }
}