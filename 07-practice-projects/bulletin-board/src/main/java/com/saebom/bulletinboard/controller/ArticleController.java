package com.saebom.bulletinboard.controller;

import com.saebom.bulletinboard.domain.Article;
import com.saebom.bulletinboard.domain.Comment;
import com.saebom.bulletinboard.dto.article.ArticleCreateForm;
import com.saebom.bulletinboard.dto.article.ArticleUpdateForm;
import com.saebom.bulletinboard.dto.comment.CommentCreateForm;
import com.saebom.bulletinboard.dto.comment.CommentUpdateForm;
import com.saebom.bulletinboard.service.ArticleService;
import com.saebom.bulletinboard.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    public ArticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @GetMapping
    public String list(Model model) {
        List<Article> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @GetMapping("/{id}")
    public String detail(
            @PathVariable Long id,
            @RequestParam(value = "editCommentId", required = false) Long editCommentId,
            Model model
    ) {
        Article article = articleService.getArticle(id);
        List<Comment> comments = commentService.getCommentsByArticle(id);

        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        model.addAttribute("commentCreateForm", new CommentCreateForm());

        if (editCommentId != null) {
            Comment comment = commentService.getComment(editCommentId);
            CommentUpdateForm commentUpdateForm = new CommentUpdateForm();
            commentUpdateForm.setContent(comment.getContent());
            model.addAttribute("commentUpdateForm", commentUpdateForm);
            model.addAttribute("editCommentId", editCommentId);
        }

        return "articles/detail";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("articleCreateForm", new ArticleCreateForm());
        return "articles/new";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("articleCreateForm") ArticleCreateForm form,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "articles/new";
        }

        // TODO: Security 적용 후 현재 로그인 사용자 ID 사용
        Long tempMemberId = 2L;

        Long articleId = articleService.createArticle(
                tempMemberId,
                form.getTitle(),
                form.getContent()
        );

        return "redirect:/articles/" + articleId;
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Article article = articleService.getArticle(id);

        ArticleUpdateForm form = new ArticleUpdateForm();
        form.setTitle(article.getTitle());
        form.setContent(article.getContent());

        model.addAttribute("articleUpdateForm", form);
        model.addAttribute("articleId", id);

        return "articles/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("articleUpdateForm") ArticleUpdateForm form,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("articleId", id);
            return "articles/edit";
        }

        // TODO: 권한 체크는 나중에 Security에서 처리
        articleService.updateArticle(id, form.getTitle(), form.getContent());

        return "redirect:/articles/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        // TODO: 작성자 본인 + 관리자 체크는 Security 적용 후 처리
        articleService.deleteArticle(id);
        return "redirect:/articles";
    }

}