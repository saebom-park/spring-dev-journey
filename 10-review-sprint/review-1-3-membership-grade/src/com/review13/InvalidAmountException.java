package com.review13;

public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException() {
        super("0원 이하의 상품은 구매할 수 없습니다.");
    }

    public InvalidAmountException(String message) {
        super(message);
    }
}