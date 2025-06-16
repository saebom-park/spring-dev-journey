package oop;

public class SMSSender extends MessageSender {
    @Override
    public void send() {
        super.send();
        System.out.println("Sending a text message");
    }
}