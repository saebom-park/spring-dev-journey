package oop;

public class EmailSender extends MessageSender {
    @Override
    public void send() {
        super.send();
        System.out.println("Sending an email.");
    }
}