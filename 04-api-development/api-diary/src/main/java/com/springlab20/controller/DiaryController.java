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
import java.util.List;
import java.util.stream.Collectors;

// [API-1] ResponseEntity 사용
import org.springframework.http.ResponseEntity;
import java.net.URI;
// [API-2] Valid 어노테이션, BindingResult 사용
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.context.support.DefaultMessageSourceResolvable;


@RestController
@RequestMapping("/api/diaries")
public class DiaryController {
    private final DiaryService diaryService;

    // constructor
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping
    public ResponseEntity<?> createDiary(@Valid @RequestBody DiaryRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(
                    DefaultMessageSourceResolvable::getDefaultMessage
            ).toList();

            return ResponseEntity.badRequest().body(errors);
        }

        DiaryResponseDto response = diaryService.createDiary(requestDto);

        return ResponseEntity.created(
                URI.create("/api/diaries/" + response.getId())
        ).body(response);
    }

    @GetMapping
    public ResponseEntity<List<DiaryResponseDto>> getDiaries() {
        return ResponseEntity.ok(
            diaryService.getDiaries().stream().map(
                diary -> new DiaryResponseDto(diary.getId(), diary.getTitle(), diary.getContent(), diary.getCreatedDate())
            ).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiaryResponseDto> getDiaryById(@PathVariable Long id) {
        return ResponseEntity.ok(diaryService.getDiaryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiary(@PathVariable Long id, @Valid @RequestBody DiaryRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(
                    DefaultMessageSourceResolvable::getDefaultMessage
            ).toList();

            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(diaryService.updateDiary(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiary(@PathVariable Long id) {
        diaryService.deleteDiary(id);

        return ResponseEntity.noContent().build();
    }
}