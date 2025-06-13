import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderExample {
    public static void main(String[] args) {
        String filePath = "01-core-java/src/SampleFile.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            System.out.println("[파일 내용 출력]");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("파일을 읽는 도중 오류 발생: " + e.getMessage());
        }
    }
}