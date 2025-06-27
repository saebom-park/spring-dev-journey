package practice;

import java.sql.*;
import java.util.Scanner;

public class JdbcInsertMultiWithScanner {
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
            
            System.out.print("몇 권의 책 정보를 입력하시겠습니까?");
            int insertCnt = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            int[] ids = new int[insertCnt];
            String[] titles = new String[insertCnt];
            String[] authors = new String[insertCnt];

            for (int i = 0; i < insertCnt; i++) {
                System.out.println("[" + (i+1) + "번째 책 입력]");
                System.out.print("id: ");
                ids[i] = scanner.nextInt();
                scanner.nextLine();
                System.out.print("title: ");
                titles[i] = scanner.nextLine();
                System.out.print("author: ");
                authors[i] = scanner.nextLine();

                int result = stmt.executeUpdate("INSERT INTO books VALUES (" + ids[i] + ", '" + titles[i] + "', '" + authors[i] + "')");

                if (result == 0) {
                    System.out.println("데이터 입력 도중 오류가 발생했습니다: " + (i+1) + " 번 째 행");
                    break;
                }
            }

            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            System.out.println("\n[책장 목록]");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + ", " +
                        rs.getString("title") + ", " +
                        rs.getString("author")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}