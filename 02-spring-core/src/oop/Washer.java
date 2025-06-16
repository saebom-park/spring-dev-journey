package oop;

public class Washer extends Appliance {
    protected int capacity;

    public Washer(String brand, int capacity) {
        super(brand);
        this.capacity = capacity;
        System.out.println("Washer Created!");
    }

    @Override
    public void powerOn() {
        super.powerOn();
        System.out.println("capacity: " + capacity + "L");
    }
}