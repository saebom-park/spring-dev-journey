// * 실습 미션: 오버라이딩 개념을 확실히 체득!
// 1. `MessageSender` 클래스: `send()` 메서드에서 `"Sending a message..."` 출력
// 2. `EmailSender` 클래스: `send()`를 오버라이딩해서 `"Sending an email."` 출력
// 3. `SMSSender` 클래스: `send()` 오버라이딩 → `"Sending a text message."` 출력
// 4. `SenderExample` 클래스에서 `MessageSender` 타입 배열로
//    `EmailSender`, `SMSSender` 객체를 순차

package oop;

public class MessageSender {
    public void send() {
        System.out.println("Sending a message...");
    }
}