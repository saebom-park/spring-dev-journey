package com.springlab20.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="diaries")
public class Diary {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="diary_id")
    private Long id;

    private String title;

    @Lob
    private String content;

    private LocalDateTime createdDate;

    // constructor
    public Diary() {}
    public Diary(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdDate = LocalDateTime.now();
    }

    // getter
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedDate() { return createdDate; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
}