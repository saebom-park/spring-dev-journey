package com.bulletin.service;

import com.bulletin.repository.CommentRepository;
import com.bulletin.repository.UserRepository;
import com.bulletin.repository.PostRepository;
import com.bulletin.dto.CommentCreateRequestDto;
import com.bulletin.dto.CommentResponseDto;
import com.bulletin.domain.User;
import com.bulletin.domain.Post;
import com.bulletin.domain.Comment;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // constructor
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentResponseDto createComment(Long postId, CommentCreateRequestDto requestDto) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        Post post = optionalPost.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        // 사용자 임시 조회 (추후 변경 예정)
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));

        Comment comment = new Comment(requestDto.getComment(), post, user);
        commentRepository.save(comment);

        //UserReposeDto
        //return new CommentResponseDto(comment.getId(), comment.getComment(), comment.getAuthor())
    }
}