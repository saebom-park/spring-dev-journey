public class SignUpMain {
    public static void main(String[] args) {
        String[] members = {"봄이", "온이", "admin"};
        int[] ages = {12, 20, 3};
        SignUpService service = new SignUpService();

        for (int i = 0; i < members.length; i++) {
            try {
                service.signUp(members[i], ages[i]);
            } catch (CustomAgeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("프로그램 종료.");
            }
        }

        int j = 0;
        do {
            try {
                service.signUp2(members[j], ages[j]);
            } catch (CustomAgeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("프로그램 종료.");
            }
            j++;
        } while (j < members.length);
    }
}
