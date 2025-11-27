package com.saebom.bulletinboard.controller;

import com.saebom.bulletinboard.dto.comment.CommentCreateForm;
import com.saebom.bulletinboard.dto.comment.CommentUpdateForm;
import com.saebom.bulletinboard.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/articles/{articleId}/comments")
    public String create(
            @PathVariable Long articleId,
            @Valid @ModelAttribute("commentCreateForm") CommentCreateForm form,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "redirect:/articles/" + articleId;
        }

        // TODO: Security 적용 후 현재 로그인 사용자 ID 사용
        Long tempMemberId = 2L;

        Long commentId = commentService.createComment(
                articleId,
                tempMemberId,
                form.getContent()
        );

        return "redirect:/articles/" + articleId;
    }

    @PostMapping("/comments/{commentId}/edit")
    public String update(
            @PathVariable Long commentId,
            @RequestParam(value = "articleId") Long articleId,
            @Valid @ModelAttribute("commentUpdateForm") CommentUpdateForm form,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "redirect:/articles/" + articleId + "?editCommentId=" + commentId;
        }

        commentService.updateComment(commentId, form.getContent());

        return "redirect:/articles/" + articleId;
    }

    @PostMapping("/comments/{commentId}/delete")
    public String delete(
            @PathVariable Long commentId,
            @RequestParam(value = "articleId") Long articleId
    ) {
        commentService.deleteComment(commentId);
        return "redirect:/articles/" + articleId;
    }

}