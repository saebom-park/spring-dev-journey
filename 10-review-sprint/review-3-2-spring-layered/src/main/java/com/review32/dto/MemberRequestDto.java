package com.review32.dto;

public class MemberRequestDto {
    private String name;
    private String email;
    private int age;

    // constructor
    public MemberRequestDto() {}
    public MemberRequestDto(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // getter
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }

    // setter (RequestDto 에서는 불필요하지만 형식상 작성)
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setAge(int age) { this.age = age; }
}