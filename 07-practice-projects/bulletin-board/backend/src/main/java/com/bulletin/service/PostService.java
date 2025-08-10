package com.bulletin.service;

import com.bulletin.dto.PostListResponseDto;
import com.bulletin.dto.PostDetailResponseDto;
import java.util.List;

public interface PostService {
    List<PostListResponseDto> getAllPosts();
    PostDetailResponseDto getPostById(Long id);
}