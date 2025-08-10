package com.bulletin.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="user_name")
    private String userName;

    @Column(name="nick_name")
    private String nickName;

    @Column(name="created_at")
    private LocalDate createdAt;
    
    // 읽기 전용 필드
    @OneToMany(mappedBy="author")
    private List<Post> posts = new ArrayList<>();

    // 일기 전용 필드
    @OneToMany(mappedBy="author")
    private List<Comment> comments = new ArrayList<>();

    // constructor
    public User() {}
    public User(String userName, String nickName, LocalDate createdAt) {
        this.userName = userName;
        this.nickName = nickName;
        this.createdAt = createdAt;
    }

    // getter
    public Long getId() { return id;}
    public String getUserName() { return userName; }
    public String getNickName() { return nickName; }
    public LocalDate getCreatedAt() { return createdAt; }
    public List<Post> getPosts() { return posts; }

    // 사용자 편의 메서드
    public void addPost(Post post) {
        posts.add(post);
        post.setAuthor(this);
    }
    
    // 사용자 편의 메서드
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setAuthor(this);
    }

}