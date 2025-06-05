public class Counter {
    static int globalCount = 0; // static 변수
    int localCount = 0; // 인스턴트 변수

    public void increase() {
        globalCount++;  // 모든 객체가 공유
        localCount++;   // 각 객체마다 따로 존재
    }

    public void print() {
        System.out.println("globalCount: " + globalCount);
        System.out.println("localCount: " + localCount);
    }
}
