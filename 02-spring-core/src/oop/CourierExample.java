package oop;

public class CourierExample {
    public static void main(String[] args) {
        DeliveryCustomer dc = new DeliveryCustomer("봄이", "키보드");
        DeliveryVehicle dv = new DeliveryVehicle();
        Courier c = new Courier("온이", dv);

        dc. requestDelivery(c);
    }
}