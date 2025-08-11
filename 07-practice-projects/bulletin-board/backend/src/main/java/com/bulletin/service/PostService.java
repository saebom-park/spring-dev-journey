package com.bulletin.service;

import com.bulletin.dto.PostCreateRequestDto;
import com.bulletin.dto.PostUpdateRequestDto;
import com.bulletin.dto.PostListResponseDto;
import com.bulletin.dto.PostDetailResponseDto;
import java.util.List;

public interface PostService {
    List<PostListResponseDto> getAllPosts();
    PostDetailResponseDto getPostById(Long id);
    PostDetailResponseDto createPost(PostCreateRequestDto requestDto);
    PostDetailResponseDto updatePost(Long id, PostUpdateRequestDto requestDto);
    void deletePost(Long id);
}