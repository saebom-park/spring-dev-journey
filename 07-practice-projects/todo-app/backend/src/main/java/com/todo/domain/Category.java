package com.todo.domain;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="category_id")
    private Long id;

    private String name;

    private String color;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    // 읽기 전용 필드
    @OneToMany(mappedBy="category")
    private List<Todo> todos = new ArrayList<>();

    // constructor
    public Category() {}
    public Category(String name, String color, User user) {
        this.name = name;
        this.color = color;
        this.user = user;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getColor() { return color; }
    public User getUser() { return user; }
    public List<Todo> getTodos() { return todos; }

    // setter
    public void setName(String name) { this.name = name; }
    public void setColor(String color) { this.color = color; }

    // setter
    public void setUser(User user) { this.user = user; }

    // 사용자 편의 메서드
    public void addTodo(Todo todo) {
        todos.add(todo);
        todo.setCategory(this);
    }

}