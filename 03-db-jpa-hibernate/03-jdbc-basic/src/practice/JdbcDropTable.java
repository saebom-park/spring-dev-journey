package practice;

import java.sql.*;
import java.util.Scanner;

public class JdbcDropTable {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        Scanner scanner = new Scanner(System.in);

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
        ) {
            stmt.execute("CREATE TABLE students (id INT, name VARCHAR(100), major VARCHAR(100))");
            stmt.execute("INSERT INTO students VALUES (1, '봄이', '통계학')");
            stmt.execute("INSERT INTO students VALUES (2, '온이', '컴퓨터공학')");
            stmt.execute("INSERT INTO students VALUES (3, '솔이', '경제학')");

            System.out.print("테이블를 정말 삭제할까요?(Y/N): ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("Y")) {
                boolean success = !stmt.execute("DROP TABLE students");
                System.out.println("테이블이 삭제되었습니다. (성공 여부: " + success + ")");
            } else {
                System.out.println("삭제가 취소되었습니다.");
            }
            
            // DROP 후 SELECT 시도 → 예외 발생 확인
            try {
                ResultSet rs = stmt.executeQuery("SELECT * FROM students");
                while (rs.next()) {
                    System.out.println(
                            rs.getInt("id") + ", " +
                            rs.getString("name") + ", " +
                            rs.getString("major")
                    );
                }
            } catch (SQLException e) {
                System.out.println("테이블 조회 실패: " + e.getMessage());
                //e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}