package oop;

public class TicketAgent {
    private final String name;
    private final Screen screen;

    public TicketAgent(String name, Screen screen) {
        this.name = name;
        this.screen = screen;
    }

    public void requestSeat(User user) {
        System.out.println("티켓 담당자 " + name + "가 예매 요청을 접수했습니다.");
        screen.reserve();
        System.out.println(name + " 티켓 담당자가 " + user.getName() + " 사용자 에게 예매 성공을 알립니다!");
        System.out.println("[예매 완료]");
        System.out.println(user.getBookingInfo());
    }
}