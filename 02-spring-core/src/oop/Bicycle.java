package oop;

// middle class
public class Bicycle extends Vehicle {
    protected String pedalType;

    public Bicycle(String name, String pedalType) {
        super(name);
        this.pedalType = pedalType;
        System.out.println("Bicycle created!");
    }

    @Override
    public void move() {
        super.move();
        System.out.println("Pedal type: " + pedalType);
    }
}