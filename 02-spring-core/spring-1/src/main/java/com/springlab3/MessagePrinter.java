package com.springlab3;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MessagePrinter {
    @Autowired
    private  MessageFormatter messageFormatter;

    public void print() {
        String message = messageFormatter.format();
        System.out.println(message);
    }
}