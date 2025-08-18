package com.springlab20.domain;

import jakarta.persistence.*;

@Entity
@Table(name="todos")
public class Todo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="todo_id")
    private Long id;

    private String title;

    boolean completed;

    // constructor
    public Todo() {}
    public Todo(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    // getter
    public Long getId() { return id; }
    public String getTitle() { return title; }
    // boolean 타입일 땐 get 대신 is 사용
    public boolean isCompleted() { return completed; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}