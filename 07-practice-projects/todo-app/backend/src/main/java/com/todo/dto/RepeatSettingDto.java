package com.todo.dto;

import com.todo.enums.RepeatPattern;
import java.time.LocalDate;
import java.util.Set;

public class RepeatSettingDto {
    private Long id;
    private boolean isRepeated;
    private LocalDate repeatStart;
    private LocalDate repeatDue;
    private RepeatPattern repeatPattern;
    private Set<Integer> dayOfWeek;

    // constructor
    public RepeatSettingDto() {}
    public RepeatSettingDto(Long id, boolean isRepeated, LocalDate repeatStart, LocalDate repeatDue, RepeatPattern repeatPattern, Set<Integer> dayOfWeek) {
        this.id = id;
        this.isRepeated = isRepeated;
        this.repeatStart = repeatStart;
        this.repeatDue = repeatDue;
        this.repeatPattern = repeatPattern;
        this.dayOfWeek = dayOfWeek;
    }

    // getter
    public Long getId() { return id; }
    public boolean getIsRepeated() { return isRepeated; }
    public LocalDate getRepeatStart() { return repeatStart; }
    public LocalDate getRepeatDue() { return repeatDue; }
    public RepeatPattern getRepeatPattern() { return repeatPattern; }
    public Set<Integer> getDayOfWeek() { return dayOfWeek; }
}