import java.sql.*;

public class AccountTransfer {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
        ) {
            stmt.execute("CREATE TABLE accounts (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100), balance INT)");
            stmt.executeUpdate("INSERT INTO accounts (name, balance) VALUES ('A', 100)");
            stmt.executeUpdate("INSERT INTO accounts (name, balance) VALUES ('B', 3000)");

            conn.setAutoCommit(false);  //트랜잭션 시작

            ResultSet rs = stmt.executeQuery("SELECT name, balance FROM accounts");
            int balanceA = 0;
            int balanceB = 0;
            while (rs.next()) {
                if (rs.getString("name").equalsIgnoreCase("A")) {
                    balanceA = rs.getInt("balance");
                } else {
                    balanceB = rs.getInt("balance");
                }
            }

            if (balanceA >= 1000) {
                String sql1 = "UPDATE accounts SET balance = balance - ? WHERE name = 'A'";
                String sql2 = "UPDATE accounts SET balance = balance + ? WHERE name = 'B'";

                try (
                    PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                    PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                ) {
                    pstmt1.setInt(1, 1000);
                    pstmt1.executeUpdate();

                    pstmt2.setInt(1, 1000);
                    pstmt2.executeUpdate();

                    conn.commit();
                    System.out.println("이체 성공!");
                } catch (SQLException rollbackEx) {
                    conn.rollback();
                    System.out.println("이체 실패! 롤백됨.");
                    rollbackEx.printStackTrace();
                }
            } else {
                conn.rollback();
                System.out.println("계좌의 잔액이 부족합니다. 이체 실패! 롤백됨.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}