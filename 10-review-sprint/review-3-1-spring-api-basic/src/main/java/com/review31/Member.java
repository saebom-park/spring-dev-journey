package com.review31;

// 회원 정보를 담는 순수 자바 객체 (POJO)
// DB 저장용이 아닌, 메모리 저장소용 Entity

public class Member {
    private Long id;
    private String name;
    private String email;
    private int age;

    // constructor
    public Member() {}
    public Member(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setAge(int age) { this.age = age; }
}