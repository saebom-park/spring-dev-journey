package practice;

import java.sql.*;
import java.util.Scanner;

public class JdbcInsertWithDuplicateCheck {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password =  "";

        Scanner scanner = new Scanner(System.in);

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt =  conn.createStatement();
        ) {
            stmt.execute("CREATE TABLE books (id INT, title VARCHAR(100), author VARCHAR(100))");
            stmt.execute("INSERT INTO books VALUES (1, '자바의 정석', '남궁성')");
            stmt.execute("INSERT INTO books VALUES (2, 'Spring Dev Journey', '봄이')");

            int i = 0;
            int inputId = 0;

            while (i < 3) {
                System.out.print("추가할 책의 ID를 입력하세요: ");
                inputId = scanner.nextInt();
                scanner.nextLine();

                if (inputId > 0) {
                    //ResultSet rs = stmt.executeQuery("SELECT Count(*) FROM books WHERE id =" + inputId);
                    ResultSet rs = stmt.executeQuery("SELECT Count(*) AS cnt FROM books WHERE id =" + inputId);
                    rs.next();
                   //int count = rs.getInt(1);  // 인덱스 처리 방식
                    int count = rs.getInt("cnt");

                    if (count > 0) {
                        System.out.println("이미 존재하는 ID 입니다.(" + (i + 1) + "/3)");
                        if (i == 2) {
                            System.out.println("책의 ID를 다시 확인해주세요. 시스템을 종료합니다.");
                            return;
                        }
                        i ++;
                    } else {
                        break;
                    }
                } else {
                    System.out.println("유효하지 않은 ID 입니다. 책의 ID를 다시 확인해주세요.");
                    return;
                }
            }

            System.out.print("책 이름을 입력해주세요: ");
            String title = scanner.nextLine();
            System.out.print("책의 저자를 입력해주세요: ");
            String author = scanner.nextLine();

            int result = stmt.executeUpdate("INSERT INTO books VALUES (" + inputId + ", '" + title + "', '" + author +"')");
            if (result > 0) {
                System.out.println("책의 정보 입력이 완료 되었습니다.");
                ResultSet rs2 = stmt.executeQuery("SELECT * FROM books WHERE id = " + inputId);
                while (rs2.next()) {
                    System.out.println(
                            rs2.getInt("id") + ", " +
                            rs2.getString("title") + ", " +
                            rs2.getString("author")
                    );
                }
            } else {
                System.out.println("입력 중 오류가 발생했습니다. 다시 시도해주세요.");
                return;
            }

            System.out.println("입력 시스템을 종료합니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

}