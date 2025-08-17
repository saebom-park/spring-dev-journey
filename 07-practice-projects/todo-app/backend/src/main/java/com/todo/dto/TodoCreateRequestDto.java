package com.todo.dto;

import com.todo.enums.TodoPriority;
import com.todo.enums.RepeatPattern;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Set;

public class TodoCreateRequestDto {
    private String content;
    private TodoPriority priority;
    private Long categoryId;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private boolean isRepeated;
    private LocalDate repeatStart;
    private LocalDate repeatDue;
    private RepeatPattern repeatPattern;
    private Set<Integer> dayOfWeek;

    // constructor
    public TodoCreateRequestDto() {}
    public TodoCreateRequestDto(String content, TodoPriority priority, Long categoryId, LocalDateTime startDate, LocalDateTime dueDate, boolean isRepeated, LocalDate repeatStart, LocalDate repeatDue, RepeatPattern repeatPattern, Set<Integer> dayOfWeek) {
        this.content = content;
        this.priority = priority;
        this.categoryId = categoryId;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.isRepeated = isRepeated;
        this.repeatStart = repeatStart;
        this.repeatDue = repeatDue;
        this.repeatPattern = repeatPattern;
        this.dayOfWeek = dayOfWeek;
    }

    // getter
    public String getContent() { return content; }
    public TodoPriority getPriority() { return priority; }
    public Long getCategoryId() { return categoryId; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getDueDate() { return dueDate; }
    public boolean getIsRepeated() { return isRepeated; }
    public LocalDate getRepeatStart() { return repeatStart; }
    public LocalDate getRepeatDue() { return repeatDue; }
    public RepeatPattern getRepeatPattern() { return repeatPattern; }
    public Set<Integer> getDayOfWeek() { return dayOfWeek;}

}