package com.springlab4;

import org.springframework.stereotype.Component;

@Component
public class MessagePrinter {
    private final MessageFormatter messageFormatter;

    public MessagePrinter(MessageFormatter messageFormatter) {
        this.messageFormatter = messageFormatter;
    }

    public void print() {
        System.out.println(messageFormatter.format());
    }

}