package oop;

public class CafeExample {
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine("네스프레소");
        Barista barista = new Barista("온이", coffeeMachine);
        Customer customer = new Customer("봄이");
        customer.orderCoffee(barista);
    }
}