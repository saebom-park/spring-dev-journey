package com.reivew12;

public class InvalidPriceException extends Exception {
    public InvalidPriceException() {
        super("0원 이하의 앨범은 구매할 수 없습니다.");
    }

    public InvalidPriceException(String message) {
        super(message);
    }
}