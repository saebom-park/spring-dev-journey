package com.review23;

public class Main {
}

/* 🔍 피드백 요약

1. insertMultiple()은 트랜잭션을 시작하기 전에 입력값(orders)에 대한 유효성 검사를 먼저 수행해야 해.
   null 또는 빈 리스트인 경우는 굳이 트랜잭션을 시작할 필요가 없기 때문에,
   early return 처리로 불필요한 자원 낭비를 방지할 수 있어.

2. 예외 발생 시 catch 블록에서 단순 로그만 출력하지 않고,
   throw e;를 통해 예외를 다시 호출자에게 전달하면
   Main 쪽에서 try-catch로 제어 가능한 구조가 돼. (예: 실패 메시지 출력, 로깅 등)
   → 실무에서는 이런 "예외 전파"가 굉장히 중요해!

*/