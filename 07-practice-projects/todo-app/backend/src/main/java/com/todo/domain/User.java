package com.todo.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;

    // 읽기 전용 필드
    @OneToMany(mappedBy="user")
    private List<Category> categories = new ArrayList<>();

    // 읽기 전용 필드
    @OneToMany(mappedBy="user")
    private List<Todo> todos = new ArrayList<>();

    // constructor
    public User() {}
    public User(String userName, String nickName) {
        this.userName = userName;
        this.nickName = nickName;
        this.createdAt = LocalDateTime.now();
    }

    // getter
    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public String getNickName() { return nickName; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<Category> getCategories() { return categories; }
    public List<Todo> getTodos() { return todos; }

    // setter
    public void setUserName(String userName) { this.userName = userName; }
    public void setNickName(String nickName) { this.nickName = nickName; }

    // 사용자 편의 메서드
    public void addCategory(Category category) {
        categories.add(category);
        category.setUser(this);
    }

    // 사용자 편의 메서드
    public void addTodo(Todo todo) {
        todos.add(todo);
        todo.setUser(this);
    }

}