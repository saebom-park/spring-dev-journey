# [OOP-8단계] 실습 미션 2: 객체 협력 구조 설계 문제 (MovieBookingExample.java)

### 🧩 시나리오

🎬 영화 예매 시스템을 구현해보자!

### ❗ 요구 사항

- `User`는 `TicketAgent`를 통해 **영화 티켓을 예매**한다.
- `TicketAgent`는 **상영관(Screen)** 에 **좌석 예약 요청**을 보낸다.
- `Screen`은 좌석을 예약하고, **예매 성공 여부**를 다시 `TicketAgent`에게 알려준다.
- `TicketAgent`는 예매 결과를 `User`에게 **최종 알림**한다.

---

### ✨ 기대 출력 예시

```
봄이 사용자가 영화 '파묘'를 예매합니다.
티켓 담당자 온이가 예매 요청을 접수했습니다.
상영관에서 좌석을 예약 중입니다...
좌석 예약이 완료되었습니다.
온이 티켓 담당자가 봄이 사용자에게 예매 성공을 알립니다!
```

---

### 🛠️ 설계 조건

- `User` 클래스 → `bookMovie(TicketAgent agent)`
- `TicketAgent` 클래스 → `requestSeat(User user)`
- `Screen` 클래스 → `reserve(String movieTitle)`
- `TicketAgent`는 `Screen`을 생성자에서 주입받아야 함
- `User`는 `TicketAgent`에게만 메시지를 보내야 함
- 최종 예매 결과는 `TicketAgent`가 `User`에게 알려줘야 함

---

### ✅ 포인트

- 객체 간 협력 흐름: **User → TicketAgent → Screen → TicketAgent → User**
- `TicketAgent`는 중간 조정자 역할: 요청 받고 전달하고 결과 알리는 구조
- 메시지 체인이 내부에서 자연스럽게 이어져야 함
- 출력은 현실적인 서비스 흐름처럼 자연스럽게 설계

---

## 📁 클래스명 제안

| 역할 | 클래스명 |
| --- | --- |
| 사용자 | `User.java` |
| 티켓 담당자 | `TicketAgent.java` |
| 상영관 | `Screen.java` |
| 실행부 | `MovieBookingExample.java` |