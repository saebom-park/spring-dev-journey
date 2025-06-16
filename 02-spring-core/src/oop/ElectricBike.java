package oop;

public class ElectricBike extends Bicycle {
    private int batteryLevel;

    public ElectricBike(String name, String pedalType, int batteryLevel) {
        super(name, pedalType);
        this.batteryLevel = batteryLevel;
        System.out.println("ElectricBike created!");
    }

    @Override
    public void move() {
        super.move();
        System.out.println("Battery level: " + batteryLevel + "%");
    }

    public static void main(String[] args) {
        ElectricBike ebike = new ElectricBike("bommmBike", "Clipless", 95);
        ebike.move();
    }
}