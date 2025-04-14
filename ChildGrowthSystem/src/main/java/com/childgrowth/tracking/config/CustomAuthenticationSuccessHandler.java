package com.childgrowth.tracking.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            switch (role) {
                case "ROLE_ADMIN":
                    response.sendRedirect("/admin/dashboard");
                    return;
                case "ROLE_DOCTOR":
                    response.sendRedirect("/doctor/dashboard");
                    return;
                case "ROLE_MEMBER":
                    response.sendRedirect("/member/dashboard");
                    return;
            }
        }
        // Nếu không có role phù hợp, chuyển về trang chủ
        response.sendRedirect("/index");
    }
}
