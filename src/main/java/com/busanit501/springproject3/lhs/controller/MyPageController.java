package com.busanit501.springproject3.lhs.controller;

import com.busanit501.springproject3.lhs.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users/mypage")
@Log4j2
@RequiredArgsConstructor // 생성자 주입을 위한 어노테이션
public class MyPageController {

    private final MyPageService myPageService; // 생성자 주입 방식으로 변경

    // 마이페이지 접근
    @GetMapping
    public String showMyPage() {
        log.info("Accessing MyPage");
        return "user/mypage"; // mypage.html 뷰를 반환
    }

    // 회원탈퇴 처리
    @GetMapping("/deleteAccount")
    public String deleteAccount(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            String username = userDetails.getUsername();
            log.info("User {} is deleting account.", username);

            try {
                myPageService.deleteUserByUsername(username); // 유저 탈퇴 처리
                log.info("User {} successfully deleted.", username);
                return "redirect:/users/logout"; // 탈퇴 후 로그아웃
            } catch (Exception e) {
                log.error("Error occurred while deleting user {}: {}", username, e.getMessage());
                return "redirect:/users/mypage?error=true"; // 오류 발생 시 마이페이지로 리다이렉트
            }
        } else {
            log.warn("Unauthenticated user attempted to delete account.");
            return "redirect:/login"; // 인증되지 않은 사용자는 로그인 페이지로 리다이렉트
        }
    }
}
