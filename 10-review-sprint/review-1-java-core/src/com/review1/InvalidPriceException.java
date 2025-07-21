package com.review1;

public class InvalidPriceException extends Exception {
    public InvalidPriceException() {
        super("유효하지 않은 가격입니다.");
    }

    public InvalidPriceException(String message) {
        super(message);
    }
}