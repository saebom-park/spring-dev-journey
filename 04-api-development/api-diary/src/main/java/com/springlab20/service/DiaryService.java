package com.springlab20.service;

import com.springlab20.dto.DiaryRequestDto;
import com.springlab20.dto.DiaryResponseDto;
import java.util.List;

public interface DiaryService {
    DiaryResponseDto createDiary(DiaryRequestDto requestDto);
    List<DiaryResponseDto> getDiaries();
    DiaryResponseDto getDiaryById(Long id);
    DiaryResponseDto updateDiary(Long id, DiaryRequestDto requestDto);
    void deleteDiary(Long id);
}