package com.todo.domain;

import com.todo.enums.TodoStatus;
import com.todo.enums.TodoPriority;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="todos")
public class Todo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="todo_id")
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private TodoStatus status;

    @Enumerated(EnumType.STRING)
    private TodoPriority priority;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="completed_at")
    private LocalDateTime completedAt;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="schedule_id")
    private Schedule schedule;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="repeat_id")
    private RepeatSetting repeatSetting;

    // constructor
    public Todo() {}
    public Todo(String content, TodoPriority priority, Category category, User user) {
        this.content = content;
        this.status = TodoStatus.PENDING;
        this.priority = priority;
        this.createdAt = LocalDateTime.now();
        this.category = category;
        this.user = user;
    }

    // getter
    public Long getId() { return id; }
    public String getContent() { return content; }
    public TodoStatus getStatus() { return status; }
    public TodoPriority getPriority() { return priority; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public Category getCategory() { return category; }
    public User getUser() { return user; }
    public Schedule getSchedule() { return schedule; }
    public RepeatSetting getRepeatSetting() { return repeatSetting; }

    // setter
    public void setContent(String content) { this.content = content; }
    public void setStatus(TodoStatus status) { this.status = status; }
    public void setPriority(TodoPriority priority) { this.priority = priority; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
    public void setCategory(Category category) { this.category = category; }
    public void setUser(User user) { this.user = user; }
    public void setSchedule(Schedule schedule) { this.schedule = schedule; }
    public void setRepeatSetting(RepeatSetting repeatSetting) { this.repeatSetting = repeatSetting; }

}