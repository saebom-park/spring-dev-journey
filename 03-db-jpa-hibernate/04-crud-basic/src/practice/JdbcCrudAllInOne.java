package practice;

import java.sql.*;
import java.util.Scanner;

public class JdbcCrudAllInOne {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        Scanner scanner = new Scanner(System.in);

        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
        ) {
            stmt.execute("CREATE TABLE books (id INT, title VARCHAR(100), author VARCHAR(100))");

            // INSERT
            int insertCnt = 0;
            insertCnt = stmt.executeUpdate("INSERT INTO books VALUES (1, '자바의 정석', '남궁성')");
            insertCnt += stmt.executeUpdate("INSERT INTO books VALUES (2, 'spring dev journey', '봄이')");
            insertCnt += stmt.executeUpdate("INSERT INTO books VALUES (3, 'spring trading lab', 'Saebom')");
            
            System.out.println("총 " + insertCnt + " 건의 데이터가 입력 되었습니다.");

            // SELECT ALL
            System.out.println("[책 전체 정보]");
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + ", "  +
                        rs.getString("title") + ", " +
                        rs.getString("author")
                );
            }

            // SELECT ONE
            System.out.print("\n조회할 책의 ID를 입력해주세요: ");
            int selectId = scanner.nextInt();
            scanner.nextLine();
            rs = stmt.executeQuery("SELECT * FROM books WHERE id = " + selectId);

            if (rs.next()) {
                System.out.println(
                        rs.getInt("id") + ", " +
                        rs.getString("title") + ", " +
                        rs.getString("author")
                );
            } else {
                System.out.println("조회되는 책 정보가 없습니다.");
            }

            // UPDATE
            System.out.print("\n수정할 책의 ID를 입력해주세요: ");
            int updateId = scanner.nextInt();
            scanner.nextLine();

            System.out.println("[수정 정보 입력]");

            System.out.print("책 제목: ");
            String updateTitle = scanner.nextLine();

            System.out.print("책 저자: ");
            String updateAuthor = scanner.nextLine();

            int updateCnt = stmt.executeUpdate("UPDATE books SET title = '" + updateTitle + "', author = '" + updateAuthor + "' WHERE id = " + updateId);
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM books WHERE id = " + updateId);
            if (updateCnt > 0) {
                System.out.println("책 정보가 수정되었습니다.");
                while (rs2.next()) {
                    System.out.println(
                            rs2.getInt("id") + ", " +
                            rs2.getString("title") + ", " +
                            rs2.getString("author")
                    );
                }
            } else {
                System.out.println("수정된 책 정보가 없습니다.");
            }

            // DELETE
            System.out.print("\n삭제할 책의 ID를 입력해주세요: ");
            int deleteId = scanner.nextInt();
            scanner.nextLine();

            int deleteCnt = stmt.executeUpdate("DELETE FROM books WHERE id = " + deleteId);
            if (deleteCnt > 0) {
                System.out.println("해당 ID의 책 정보가 삭제 되었습니다.");
            } else {
                System.out.println("삭제된 책 정보가 없습니다.");
            }
            
            // Select All
            System.out.println("\n[최종 전체 책 정보]");
            ResultSet rs3 = stmt.executeQuery("SELECT * FROM books");
            while (rs3.next()) {
                System.out.println(
                        rs3.getInt("id") + ", " +
                        rs3.getString("title") + ", " +
                        rs3.getString("author")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}