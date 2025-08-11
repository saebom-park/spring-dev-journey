package com.bulletin.dto;

public class CommentCreateRequestDto {
    private String comment;

    // constructor
    public CommentCreateRequestDto() {}
    public CommentCreateRequestDto(String comment) { this.comment = comment; }

    // getter
    public String getComment() { return comment; }
}