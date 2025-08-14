package com.todo.domain;

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
    @OneToMany(mappedBy="user")
    private List<Todo> todos = new ArrayList<>();

    // constructor
    public User() {}
    public User(String userName, String nickName) {
        this.userName = userName;
        this.nickName = nickName;
        this.createdAt = LocalDate.now();
    }

    // getter
    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public String getNickName() { return nickName;}
    public LocalDate getCreatedAt() { return createdAt; }
    public List<Todo> getTodos() { return todos; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setNickName(String nickName) { this.nickName = nickName; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

    // 사용자 편의 메서드
    public void addTodos() {}
}