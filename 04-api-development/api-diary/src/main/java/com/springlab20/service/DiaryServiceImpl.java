package com.springlab20.service;

import com.springlab20.repository.DiaryRepository;
import com.springlab20.dto.DiaryRequestDto;
import com.springlab20.dto.DiaryResponseDto;
import com.springlab20.domain.Diary;
import org.springframework.stereotype.Service;
// [API-1] Transactional 사용
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;

    // constructor
    public DiaryServiceImpl(DiaryRepository diaryRepository) { this.diaryRepository = diaryRepository; }

    @Override
    public DiaryResponseDto createDiary(DiaryRequestDto requestDto) {
        Diary diary = new Diary(requestDto.getTitle(), requestDto.getContent());
        diaryRepository.save(diary);

        return getDiaryResponseDto(diary);
    }

    @Override
    @Transactional(readOnly=true)
    public List<DiaryResponseDto> getDiaries() {
        return diaryRepository.findAll().stream().map(
                this::getDiaryResponseDto
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly=true)
    public DiaryResponseDto getDiaryById(Long id) {
        Optional<Diary> optionalDiary = diaryRepository.findById(id);
        Diary diary = optionalDiary.orElseThrow(() -> new IllegalArgumentException("조회되는 다이어리가 없습니다."));

        return getDiaryResponseDto(diary);
    }

    @Override
    public DiaryResponseDto updateDiary(Long id, DiaryRequestDto requestDto) {
        Optional<Diary> optionalDiary = diaryRepository.findById(id);
        Diary diary = optionalDiary.orElseThrow(() -> new IllegalArgumentException("조회되는 다이어리가 없습니다."));

        diary.setTitle(requestDto.getTitle());
        diary.setContent(requestDto.getContent());
        // [API-1] Dirty Checking에 의해 update SQL 자동 실행
        return getDiaryResponseDto(diary);
    }

    @Override
    public void deleteDiary(Long id) {
        Optional<Diary> optionalDiary = diaryRepository.findById(id);
        Diary diary = optionalDiary.orElseThrow(() -> new IllegalArgumentException("조회되는 다이어리가 없습니다."));

        diaryRepository.delete(diary);
    }

    private DiaryResponseDto getDiaryResponseDto(Diary diary) {
        return new DiaryResponseDto(diary.getId(), diary.getTitle(), diary.getContent(), diary.getCreatedDate());
    }
}