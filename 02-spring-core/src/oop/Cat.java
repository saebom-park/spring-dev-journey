package oop;

public class Cat extends Animal {
    @Override
    public void speak() {
        System.out.println("야옹!");
    }

    public static void main(String[] args) {
        Animal a1 = new Animal();
        Animal a2 = new Cat();

        a1.speak();
        a2.speak();
        //a2.super.speak(); // 자식 클래스 내부에서만 부모 메서드 호출 가능
    }
}