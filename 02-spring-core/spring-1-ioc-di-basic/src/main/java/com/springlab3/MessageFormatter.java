package com.springlab3;

import org.springframework.stereotype.Component;

@Component
public class MessageFormatter {
    private final MessageService messageService;

    public MessageFormatter(MessageService messageService) {
        this.messageService = messageService;
    }

    public String format() {
        String message = "📢 메세지: ";
        message = message + messageService.getMessage();

        return message;
    }
}