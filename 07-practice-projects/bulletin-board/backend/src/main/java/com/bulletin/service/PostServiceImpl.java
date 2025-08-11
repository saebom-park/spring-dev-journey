package com.bulletin.service;

import com.bulletin.repository.UserRepository;
import com.bulletin.repository.PostRepository;
import com.bulletin.dto.PostCreateRequestDto;
import com.bulletin.dto.PostListResponseDto;
import com.bulletin.dto.PostDetailResponseDto;
import com.bulletin.dto.CommentResponseDto;
import com.bulletin.dto.UserResponseDto;
import com.bulletin.domain.User;
import com.bulletin.domain.Post;
import com.bulletin.domain.Comment;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // constructor
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PostListResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostListResponseDto> postListResponseDtos = new ArrayList<>();

        for (Post post : posts) {
            UserResponseDto userResponseDto = new UserResponseDto(post.getAuthor().getId(), post.getAuthor().getNickName());
            PostListResponseDto postListResponseDto = new PostListResponseDto(post.getId(), post.getTitle(), userResponseDto, post.getCreatedAt(), post.getViews());
            postListResponseDtos.add(postListResponseDto);
        }

        return postListResponseDtos;
    }

    @Override
    public PostDetailResponseDto getPostById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = optionalPost.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        UserResponseDto userResponseDto = new UserResponseDto(post.getAuthor().getId(), post.getAuthor().getNickName());
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            UserResponseDto userCommentResponseDto = new UserResponseDto(comment.getAuthor().getId(), comment.getAuthor().getNickName());
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment.getId(), comment.getComment(), userCommentResponseDto, comment.getCreatedAt());
            commentResponseDtos.add(commentResponseDto);
        }
        return new PostDetailResponseDto(post.getId(), post.getTitle(), post.getContent(), userResponseDto, post.getCreatedAt(), post.getViews(), commentResponseDtos);
    }

    @Override
    public PostDetailResponseDto createPost(PostCreateRequestDto requestDto) {
        // 사용자 임시 조회 (추후 변경 예정)
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));
        Post post = new Post(requestDto.getTitle(), requestDto.getContent(), user);
        postRepository.save(post);
        UserResponseDto userResponseDto = new UserResponseDto(post.getAuthor().getId(), post.getAuthor().getNickName());
        return new PostDetailResponseDto(post.getId(), post.getTitle(), post.getContent(), userResponseDto, post.getCreatedAt(), post.getViews(), new ArrayList<CommentResponseDto>());
    }
}