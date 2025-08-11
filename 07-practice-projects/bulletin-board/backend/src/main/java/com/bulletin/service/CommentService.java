package com.bulletin.service;

import com.bulletin.dto.CommentCreateRequestDto;
import com.bulletin.dto.CommentResponseDto;

public interface CommentService {
    CommentResponseDto createComment(Long postId, CommentCreateRequestDto requestDto);
    void deleteComment(Long commentId);
}