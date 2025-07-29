package com.review31;

public class MemberRequestDto {
    private String name;
    private String email;
    private int age;

    // 기본 생성자 (Jackson이 필요로 함)
    public MemberRequestDto() {}

    // 전체 필드 생성자
    public MemberRequestDto(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // getter
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }

    // setter
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) {this.email = email; }
    public void setAge(int age) { this.age = age; }
}