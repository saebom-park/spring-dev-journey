public class CounterMain {
    public static void main(String[] args) {
        Counter a = new Counter();
        Counter b = new Counter();

        a.increase();   // global +1, local +1 (a)
        b.increase();   // global +1, local +1 (b)

        a.print();  // global: 2, local: 1
        b.print();  // global: 2, local: 1
        System.out.println(Counter.globalCount);    // static 변수 → 클래스명.변수명도 가능
        System.out.println(a.localCount);   // 인스턴트 변수 → 인스턴트.변수명만 가능
    }
}
