package practice;

import java.sql.*;
import java.util.ArrayList;

public class JdbcMultiInsert {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        String[] names = {"봄이", "온이", "솔이", "현이", "민이"};
        String[] majors = {"통계학", "컴퓨터공학", "경제학", "영문학", "사회학"};

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement()
        ) {
            stmt.execute("CREATE TABLE students (id INT, name VARCHAR(100), major VARCHAR(100))");

            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                String major = majors[i];
                stmt.execute("INSERT INTO students VALUES (" + (i+1) + ", '" + names[i] + "', '" + majors[i] + "')");
            }
            System.out.println("총 5 건의 학생 정보를 입력했습니다.");

            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + ", " +
                        rs.getString("name") + ", " +
                        rs.getString("major")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}