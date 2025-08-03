package com.review51.dto;

public class MemberRequestDto {
    private String name;

    // constructor
    public MemberRequestDto() {}
    public MemberRequestDto(String name) { this.name = name; }

    // getter
    public String getName() { return name; }
    
    // requestDto는 setter 불필요
}