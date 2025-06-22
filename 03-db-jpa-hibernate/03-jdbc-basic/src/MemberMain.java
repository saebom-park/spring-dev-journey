import java.sql.*;

public class MemberMain {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement()
        ) {
            stmt.execute("CREATE TABLE members (id INT, name VARCHAR(50), email VARCHAR(100));");
            stmt.execute("INSERT INTO members VALUES (1, '봄이', 'from.saebom@gmail.com');");

            ResultSet rs = stmt.executeQuery("SELECT * FROM members;");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", " + rs.getString("name") + ", " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}