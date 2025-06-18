package oop;

public class Customer {
    String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void orderCoffee(Barista barista) {
        System.out.println(name + " 고객이 커피를 주문합니다.");
        barista.makeCoffee(this);
    }
}