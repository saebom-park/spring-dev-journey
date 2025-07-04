package com.springlab4;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MessageFormatter {
    @Autowired
    private MessageService messageService;

    @PostConstruct
    public void init() {
        System.out.println("🔨 Formatter 초기화 완료 (PostConstruct 실행됨)");
    }

    public String format() {
        String message = messageService.getMessage();
        return "📢 메세지: " + message;
    }
}