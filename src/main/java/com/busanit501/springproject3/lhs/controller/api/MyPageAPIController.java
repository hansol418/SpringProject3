package com.busanit501.springproject3.lhs.controller.api;

import com.busanit501.springproject3.lhs.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/mypage")
@Log4j2
@RequiredArgsConstructor
public class MyPageAPIController {

    private final MyPageService myPageService;

    // 마이페이지 접근 (REST에서는 주로 JSON 데이터를 반환)
    @GetMapping
    public ResponseEntity<String> showMyPage(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            log.info("User {} is accessing MyPage", userDetails.getUsername());
            return ResponseEntity.ok("Welcome to your MyPage, " + userDetails.getUsername());
        } else {
            log.warn("Unauthenticated user tried to access MyPage");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login to access MyPage.");
        }
    }

    // 탈퇴 확인 페이지 대신 탈퇴 가능 여부를 JSON으로 반환
    @GetMapping("/confirmDelete")
    public ResponseEntity<String> confirmDelete(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            log.info("User {} is confirming account deletion", userDetails.getUsername());
            return ResponseEntity.ok("You can proceed to delete your account, " + userDetails.getUsername());
        } else {
            log.warn("Unauthenticated user tried to confirm account deletion.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login to confirm account deletion.");
        }
    }

    // 회원탈퇴 처리 (DELETE 방식 사용)
    @DeleteMapping("/deleteAccount")
    public ResponseEntity<String> deleteAccount(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            String username = userDetails.getUsername();
            log.info("User {} is deleting account.", username);

            try {
                myPageService.deleteUserByUsername(username);
                log.info("User {} successfully deleted.", username);
                return ResponseEntity.ok("Account successfully deleted. Please login again.");
            } catch (Exception e) {
                log.error("Error occurred while deleting user {}: {}", username, e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("An error occurred while deleting the account. Please try again later.");
            }
        } else {
            log.warn("Unauthenticated user attempted to delete account.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login to delete your account.");
        }
    }
}
