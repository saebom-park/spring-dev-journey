package com.bulletin.controller;

import com.bulletin.service.PostService;
import com.bulletin.dto.PostListResponseDto;
import com.bulletin.dto.PostDetailResponseDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PostController {
    private final PostService postService;
    // constructor
    public PostController(PostService postService) { this.postService = postService; }

    @GetMapping("/api/posts")
    public List<PostListResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostDetailResponseDto getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }
}