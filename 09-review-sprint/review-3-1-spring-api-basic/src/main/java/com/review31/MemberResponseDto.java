package com.review31;

public class MemberResponseDto {
    private Long id;
    private String name;

    // 기본 생성자 (RequestDto와 마찬가지로 리플렉션 기반 생성에 필요)
    public MemberResponseDto() {}

    // 전체 필드 생성자
    public MemberResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}