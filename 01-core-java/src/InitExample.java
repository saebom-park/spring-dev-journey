// static 초기화 예제
public class InitExample {
    static int count;
    // static 블록
    static {
        System.out.println("클래스가 처음 로드될 때 실행됨!");
        count = 100;
    }

    public static void showCount() {
        System.out.println("count = " + count);
    }

    public static void main(String[] args) {
        InitExample.showCount();    // 클래스 로딩 시 static 블록 실행
    }
}