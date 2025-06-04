public class SignUpService {
    /**
     * 회원 가입 조건 검증
     */
    public void signUp(String name, int age) throws CustomAgeException {
        if (age < 14) {
            throw new CustomAgeException(name + "는 만 14세 미만이므로 가입할 수 없습니다.");
        }
        System.out.println(name + "는 만 14세 이상이므로 가입 가능합니다.");
    }

    public void signUp2(String name, int age) throws CustomAgeException {
        if (!"admin".equals(name) && age < 14) {
            throw new CustomAgeException();
        }
        System.out.println("회원가입이 가능합니다.");
    }
}
