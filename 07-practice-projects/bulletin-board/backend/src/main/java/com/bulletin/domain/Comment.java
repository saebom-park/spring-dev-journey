package com.bulletin.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long id;

    private String comment;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="author_id")
    private User author;

    @Column(name="created_at")
    private LocalDate createdAt;

    // constructor
    public Comment() {}
    public Comment(String comment, Post post, User author) {
        this.comment = comment;
        this.post = post;
        this.author = author;
        this.createdAt = LocalDate.now();
    }

    // getter
    public Long getId() { return id; }
    public String getComment() { return comment; }
    public Post getPost() { return post; }
    public User getAuthor() { return author; }
    public LocalDate getCreatedAt() { return createdAt; }

    // setter
    public void setPost(Post post) { this.post = post; }
    public void setAuthor(User author) { this.author = author; }
}