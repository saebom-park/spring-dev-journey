package oop;

public class SenderExample {
    public static void main(String[] args) {
        MessageSender email = new EmailSender();
        MessageSender sms = new SMSSender();

        email.send();
        sms.send();
    }
}