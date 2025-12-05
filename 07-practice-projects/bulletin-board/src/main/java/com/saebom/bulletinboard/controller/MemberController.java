package com.saebom.bulletinboard.controller;

import com.saebom.bulletinboard.domain.Member;
import com.saebom.bulletinboard.dto.member.MemberCreateForm;
import com.saebom.bulletinboard.dto.member.UsernameCheckForm;
import com.saebom.bulletinboard.service.MemberService;
import com.saebom.bulletinboard.session.SessionConst;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/new")
    public String signUpForm(Model model) {
        model.addAttribute("memberCreateForm", new MemberCreateForm());
        model.addAttribute("usernameChecked", false);
        return "member/signup";
    }

    @PostMapping("/new")
    public String signUp(
            @Valid @ModelAttribute("memberCreateForm") MemberCreateForm form,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(defaultValue = "false") boolean usernameChecked,
            Model model
    ) {

        model.addAttribute("usernameChecked", usernameChecked);

        if (!bindingResult.hasFieldErrors("username")) {
            if (memberService.isUsernameDuplicate(form.getUsername())) {
                bindingResult.rejectValue("username", "duplicate", "이미 사용 중인 아이디입니다.");
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("usernameCheck", false);
            return "member/signup";
        }

        Member member = new Member();
        member.setUsername(form.getUsername());
        member.setPassword(form.getPassword());
        member.setName(form.getName());
        member.setEmail(form.getEmail());

        Long memberId = memberService.registerMember(member);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, memberId);

        return "redirect:/articles";
    }

    @GetMapping("/check-username")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkUsername(
            @Valid UsernameCheckForm form,
            BindingResult bindingResult
    ) {

        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();

            response.put("valid", false);
            response.put("duplicate", false);
            response.put("message", message);

            return ResponseEntity.badRequest().body(response);
        }

        String username = form.getUsername();
        boolean duplicate = memberService.isUsernameDuplicate(username);

        response.put("valid", !duplicate);
        response.put("duplicate", duplicate);
        response.put("message",
                duplicate ? "이미 사용 중인 아이디입니다." : "사용 가능한 아이디입니다.");

        return ResponseEntity.ok(response);
    }

}