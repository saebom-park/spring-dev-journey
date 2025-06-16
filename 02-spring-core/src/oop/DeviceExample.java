package oop;

public class DeviceExample {
    public static void main(String[] args) {
        Device[] devices = {new SmartPhone(), new FeaturePhone()};

        for (Device d : devices) {
            d.powerOn();
            if (d instanceof SmartPhone) {
                ((SmartPhone) d).runApp();
            } else if (d instanceof FeaturePhone) {
                ((FeaturePhone) d).useKeypad();
            }
        }
    }
}