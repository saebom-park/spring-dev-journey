public class LoginMain {
    public static void main(String[] args) {
        LoginService service = new LoginService();
        try {
            service.login("spring", "1234");
        } catch (CustomLoginException e) {
            System.out.println("예외발생: " + e.getMessage());
        }

    }
}
