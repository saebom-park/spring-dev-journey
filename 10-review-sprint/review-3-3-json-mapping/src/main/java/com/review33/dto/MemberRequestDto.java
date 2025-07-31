package com.review33.dto;

public class MemberRequestDto {
    private String name;
    private String email;
    private int age;

    // constructor
    public MemberRequestDto(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // getter
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    
    // RequestDto는 Setter 생략
}