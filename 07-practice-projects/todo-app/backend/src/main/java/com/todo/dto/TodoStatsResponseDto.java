package com.todo.dto;

import java.util.List;

public class TodoStatsResponseDto {
    private int totalTodos;
    private int completedTodos;
    private int pendingTodos;
    private int inProgressTodos;
    private double completionRate;
    private List<CategoryStatsDto> categoryStatsDtos;

    // constructor
    public TodoStatsResponseDto() {}
    public TodoStatsResponseDto(int totalTodos, int completedTodos, int pendingTodos, int inProgressTodos, double completionRate, List<CategoryStatsDto> categoryStatsDtos) {
        this.totalTodos = totalTodos;
        this.completedTodos = completedTodos;
        this.pendingTodos = pendingTodos;
        this.inProgressTodos = inProgressTodos;
        this.completionRate = completionRate;
        this.categoryStatsDtos = categoryStatsDtos;
    }

    // getter
    public int getTotalTodos() { return totalTodos; }
    public int getCompletedTodos() { return completedTodos; }
    public int getPendingTodos() { return pendingTodos; }
    public int getInProgressTodos() { return inProgressTodos; }
    public double getCompletionRate() { return completionRate; }
    public List<CategoryStatsDto> getCategoryStatsDto() { return categoryStatsDtos; }
}