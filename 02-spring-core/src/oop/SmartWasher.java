package oop;

public class SmartWasher extends Washer {
    protected boolean wifiEnabled;

    public SmartWasher(String brand, int capacity, boolean wifiEnabled) {
        super(brand, capacity);
        this.wifiEnabled = wifiEnabled;
        System.out.println("Smart Washer Created!");
    }

    public void powerOn() {
        super.powerOn();
        System.out.println("wifi-Enabled: " + wifiEnabled);
    }

    public static void main(String[] args) {
        SmartWasher sWasher = new SmartWasher("SamSung", 100, true);
        sWasher.powerOn();
    }
}