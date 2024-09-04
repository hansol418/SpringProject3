    package com.busanit501.springproject3.lhs.controller;

    import com.busanit501.springproject3.lhs.entity.User;
    import com.busanit501.springproject3.lhs.service.MyPageService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.Authentication;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import lombok.extern.log4j.Log4j2;

    import java.util.Optional;


    @Controller
    @RequestMapping("/users/mypage")
    @Log4j2
    public class MyPageController {

        @Autowired
        private MyPageService myPageService;

        // 회원 탈퇴 기능을 처리하는 메서드
        @PostMapping("/delete")
        public String deleteUserAccount(Authentication authentication) {
            // 현재 인증된 사용자의 사용자 이름(username)을 가져옵니다.
            String username = authentication.getName();
            Optional<User> user = myPageService.getUserByUsername(username);

            if (user.isPresent()) {
                myPageService.deleteUser(user.get().getId()); // 사용자 엔티티 삭제
                log.info("User deleted: " + username);
                return "redirect:/users/logout"; // 탈퇴 후 로그아웃 페이지로 리다이렉트
            } else {
                log.warn("Failed to delete user: User not found for username: " + username);
                return "redirect:/users/mypage"; // 탈퇴 실패 시 마이페이지로 리다이렉트
            }
        }
    }