package com.todo.dto;

public class CategoryStatsDto {
    private String categoryName;
    private int totalCount;
    private int completedCount;

    // constructor
    public CategoryStatsDto() {}
    public CategoryStatsDto(String categoryName, int totalCount, int completedCount) {
        this.categoryName = categoryName;
        this.totalCount = totalCount;
        this.completedCount = completedCount;
    }

    // getter
    public String getCategoryName() { return categoryName; }
    public int getTotalCount() { return totalCount; }
    public int getCompletedCount() { return completedCount; }
}