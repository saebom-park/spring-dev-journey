package com.review21;

// 피드백 2 수정
public class InvalidScopeException extends Exception {
    public InvalidScopeException() {
        super("입력 가능한 숫자 범위를 벗어났습니다.");
    }

    public InvalidScopeException(String message) {
        super(message);
    }
}