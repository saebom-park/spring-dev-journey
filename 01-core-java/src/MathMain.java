public class MathMain {
    public static void main(String[] args) {
        int a = MathUtil.doubleValue(5);
        System.out.println("2배: " + a);

        MathUtil util = new MathUtil();
        int b = util.tripleValue(5);
        System.out.println("3배: " + b);
    }
}
