package com.springlab;

import org.springframework.stereotype.Component;

@Component
public class Engine {
    public void start() {
        System.out.println("엔진이 가동됩니다.");
    }
}