package com.springlab2;

import org.springframework.stereotype.Component;

@Component
public class MessagePrinter {
    private final MessageService messageService;    // final: 생성자 주입 시 거의 항상 붙임

    public MessagePrinter(MessageService messageService) {
        this.messageService = messageService;
    }

    public void printMessage() {
        messageService.message();
        System.out.println("메세지를 출력합니다.");
    }
}