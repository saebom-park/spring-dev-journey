package com.springlab20.controller;

import com.springlab20.service.DiaryService;
import com.springlab20.dto.DiaryRequestDto;
import com.springlab20.dto.DiaryResponseDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
// [API-1] ResponseEntity 사용
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/diaries")
public class DiaryController {
    private final DiaryService diaryService;

    // constructor
    public DiaryController(DiaryService diaryService) { this.diaryService = diaryService; }

    @PostMapping
    public ResponseEntity<DiaryResponseDto> createDiary(@RequestBody DiaryRequestDto requestDto) {
        DiaryResponseDto response = diaryService.createDiary(requestDto);

        return ResponseEntity.created(
                URI.create("/api/diaries/" + response.getId())
        ).body(response);
    }

    @GetMapping
    public ResponseEntity<List<DiaryResponseDto>> getDiaries() {
        return ResponseEntity.ok(diaryService.getDiaries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiaryResponseDto> getDiaryById(@PathVariable Long id) {
        return ResponseEntity.ok(diaryService.getDiaryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiaryResponseDto> updateDiary(@PathVariable Long id, @RequestBody DiaryRequestDto requestDto) {
        return ResponseEntity.ok(diaryService.updateDiary(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiary(@PathVariable Long id) {
        diaryService.deleteDiary(id);

        return ResponseEntity.noContent().build();
    }
}