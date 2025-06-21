package oop;

public class MovieBookingExample {
    public static void main(String[] args) {
        User user = new User("봄이");
        Screen screen = new Screen();
        TicketAgent ticketAgent = new TicketAgent("온이", screen);
        user.bookMovie(ticketAgent);
    }
}