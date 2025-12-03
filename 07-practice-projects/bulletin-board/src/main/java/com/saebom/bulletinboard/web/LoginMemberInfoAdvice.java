package com.saebom.bulletinboard.web;

import com.saebom.bulletinboard.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class LoginMemberInfoAdvice {

    @ModelAttribute
    public void addLoginMemberInfo(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        Long loginMemberId = (session != null) ? (Long) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
        if (loginMemberId != null) {
            model.addAttribute("loginMemberId", loginMemberId);
        }

        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();

        String currentURL = (queryString != null && !queryString.isBlank())
                ? requestURI + "?" + queryString
                : requestURI;

        model.addAttribute("currentURL", currentURL);
    }

}