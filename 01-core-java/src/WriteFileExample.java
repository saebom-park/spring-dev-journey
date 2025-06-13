import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFileExample {
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("01-core-java/src/createFile.txt", true)) { // true가 있어야 append, 없으면 overwrite
        //try (FileWriter writer = new FileWriter("01-core-java/src/createFile.txt")) {
            writer.write("\n");
            writer.write("Finish Last Step soon!\n");
            writer.write("Congratulation!");
        } catch (IOException e) {
            System.out.println("파일 쓰기 중 오류 발생: " + e.getMessage());
        }

        String filePath = "01-core-java/src/createFile.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("파일 읽는 중 오류 발생: " + e.getMessage());
        }
    }
}