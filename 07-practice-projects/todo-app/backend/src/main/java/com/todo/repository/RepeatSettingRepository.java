package com.todo.repository;

import com.todo.domain.RepeatSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepeatSettingRepository extends JpaRepository<RepeatSetting, Long> {
}