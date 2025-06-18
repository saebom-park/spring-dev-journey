package oop;

public class Barista {
    String name;
    private CoffeeMachine coffeeMachine;

    public Barista(String name, CoffeeMachine coffeeMachine) {
        this.name = name;
        this.coffeeMachine = coffeeMachine;
    }

    public String getName() {
        return name;
    }

    public void makeCoffee(Customer customer) {
        System.out.println("바리스타 " + name + "가 " + customer.getName() + " 고객의 커피를 준비합니다.");
        coffeeMachine.brew(this);
    }
}