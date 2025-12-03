package com.saebom.bulletinboard.controller;

import com.saebom.bulletinboard.dto.member.LoginForm;
import com.saebom.bulletinboard.domain.Member;
import com.saebom.bulletinboard.service.MemberService;
import com.saebom.bulletinboard.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "redirectURL", required = false, defaultValue = "/articles") String redirectURL, Model model) {
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("redirectURL", redirectURL);
        return "member/login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam(value = "redirectURL", required = false, defaultValue = "/articles") String redirectURL,
            @Valid @ModelAttribute("loginForm") LoginForm loginForm,
            BindingResult bindingResult,
            HttpServletRequest request,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("redirectURL", redirectURL);
            return "member/login";
        }

        Member member = memberService.findByUsername(loginForm.getUsername());
        if (member == null || !member.getPassword().equals(loginForm.getPassword())) {
            model.addAttribute("redirectURL", redirectURL);
            model.addAttribute("loginError", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "member/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member.getId());

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/login";
    }

}