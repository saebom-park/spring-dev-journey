package oop;

public class PhoneExample {
    public static void main(String[] args) {
        Phone[] phones = {new SmartPhone(), new FeaturePhone()};

        for (Phone p : phones) {
            p.powerOn();
            if (p instanceof SmartPhone) {
                ((SmartPhone) p).runApp();
            } else if (p instanceof FeaturePhone) {
                ((FeaturePhone) p).useKeypad();
            }
        }
    }
}