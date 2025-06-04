public class LoginService {
    public void login(String username, String password) throws CustomLoginException {
        if (!"spring".equals(username) || !"1234".equals(password)) {
            throw new CustomLoginException("아이디 또는 비밀번호가 틀렸습니다.");
        }
        System.out.println("로그인 성공!");
    }
}
