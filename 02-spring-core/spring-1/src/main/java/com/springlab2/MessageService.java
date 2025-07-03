package com.springlab2;

import org.springframework.stereotype.Component;

@Component
public class MessageService {
    public void message() {
        System.out.println("Hello Spring!");
    }
}