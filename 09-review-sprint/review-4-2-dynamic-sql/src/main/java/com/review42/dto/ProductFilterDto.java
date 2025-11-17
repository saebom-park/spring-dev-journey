package com.review42.dto;

public class ProductFilterDto {
    private String keyword;
    private Long categoryId;
    private Integer minPrice;
    private Integer maxPrice;
    private String sortBy;
    private String sortDir;

    // constructor
    public ProductFilterDto() {}
    public ProductFilterDto(String keyword, Long categoryId, Integer minPrice, Integer maxPrice, String sortBy, String sortDir) {
        this.keyword = keyword;
        this.categoryId = categoryId;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.sortBy = sortBy;
        this.sortDir = sortDir;
    }

    // getter
    public String getKeyword() { return keyword; }
    public Long getCategoryId() { return categoryId; }
    public Integer getMinPrice() { return minPrice; }
    public Integer getMaxPrice() { return maxPrice; }
    public String getSortBy() { return sortBy; }
    public String getSortDir() { return sortDir; }

    // setter
    public void setKeyword(String keyword) { this.keyword = keyword; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public void setMinPrice(Integer minPrice) { this.minPrice = minPrice; }
    public void setMaxPrice(Integer maxPrice) { this.maxPrice = maxPrice; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }
    public void setSortDir(String sortDir) { this.sortDir = sortDir; }
}