public class CustomLoginException extends Exception {
    public CustomLoginException() { // 생성자 1 (기본)
        super("로그인에 실패했습니다.");
    }

    public CustomLoginException(String message) { // 생성자 2
        super(message);
    }
}
