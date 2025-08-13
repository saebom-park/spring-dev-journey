package com.bulletin.controller;

import com.bulletin.service.CommentService;
import com.bulletin.dto.CommentCreateRequestDto;
import com.bulletin.dto.CommentResponseDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:5175"})
public class CommentController {
    private final CommentService commentService;

    // constructor
    public CommentController(CommentService commentService) { this.commentService = commentService; }

    @PostMapping("/posts/{postId}/comments")
    public CommentResponseDto createComment(@PathVariable Long postId, @RequestBody CommentCreateRequestDto requestDto) {
        return commentService.createComment(postId, requestDto);
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}