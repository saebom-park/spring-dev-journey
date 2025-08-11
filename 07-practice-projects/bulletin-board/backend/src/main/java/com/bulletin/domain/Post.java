package com.bulletin.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="author_id")
    private User author;

    @Column(name="created_at")
    private LocalDate createdAt;

    private int views;

    @OneToMany(mappedBy="post")
    private List<Comment> comments = new ArrayList<>();

    // constructor
    public Post() {}
    public Post(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = LocalDate.now();
        this.views = 0;
    }

    // getter
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public User getAuthor() { return author; }
    public LocalDate getCreatedAt() { return createdAt; }
    public int getViews() { return views; }
    public List<Comment> getComments() { return comments; }

    // setter
    public void setAuthor(User author) { this.author = author; }
}