package com.springlab20.service;

import com.springlab20.dto.DiaryRequestDto;
import com.springlab20.dto.DiaryResponseDto;
import java.util.List;
// [API-5] Paging 처리
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiaryService {
    DiaryResponseDto createDiary(DiaryRequestDto requestDto);
    // [API-5] Paging 처리
    Page<DiaryResponseDto> getDiaries(Pageable pageable);
    DiaryResponseDto getDiaryById(Long id);
    DiaryResponseDto updateDiary(Long id, DiaryRequestDto requestDto);
    void deleteDiary(Long id);
}