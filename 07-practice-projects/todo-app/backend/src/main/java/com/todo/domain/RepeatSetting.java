package com.todo.domain;

import com.todo.enums.RepeatPattern;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name="repeat_settings")
public class RepeatSetting {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="repeat_id")
    private Long id;

    @Column(name="is_repeated")
    private boolean isRepeated;

    @Column(name="repeat_start")
    private LocalDate repeatStart;

    @Column(name="repeat_due")
    private LocalDate repeatDue;

    @Enumerated(EnumType.STRING)
    @Column(name="repeat_pattern")
    private RepeatPattern repeatPattern;

    @ElementCollection
    @CollectionTable(name="repeat_week_days", joinColumns=@JoinColumn(name="repeat_id"))
    @Column(name="day_of_week")
    private Set<Integer> dayOfWeek = new HashSet<>();

    // constructor
    public RepeatSetting() {}
    public RepeatSetting(boolean isRepeated, LocalDate repeatStart, LocalDate repeatDue, RepeatPattern repeatPattern, Set<Integer> dayOfWeek) {
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

    // setter
    public void setIsRepeated(boolean isRepeated) { this.isRepeated = isRepeated; }
    public void setRepeatStart(LocalDate repeatStart) { this.repeatStart = repeatStart; }
    public void setRepeatDue(LocalDate repeatDue) { this.repeatDue = repeatDue; }
    public void setRepeatPattern(RepeatPattern repeatPattern) { this.repeatPattern = repeatPattern; }
    public void setDayOfWeek(Set<Integer> dayOfWeek) { this.dayOfWeek = dayOfWeek; }

}