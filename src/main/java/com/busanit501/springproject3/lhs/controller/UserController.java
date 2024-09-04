package com.busanit501.springproject3.lhs.controller;

import com.busanit501.springproject3.lhs.entity.User;
import com.busanit501.springproject3.lhs.entity.mongoEntity.ProfileImage;
import com.busanit501.springproject3.lhs.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    // 마이페이지 보여주는 메서드
    @GetMapping("/mypage")
    public String showMyPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        // 로그인된 사용자의 정보를 가져옴
        Optional<User> userOptional = userService.getUserByUsername(userDetails.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);  // 사용자 정보를 모델에 추가
            return "user/mypage";  // 마이페이지로 이동
        }

        // 유저가 없으면 메인 페이지로 리다이렉트
        return "redirect:/main";
    }

    // 비밀번호 확인 페이지
    @GetMapping("/{id}/confirmDelete")
    public String showConfirmDeleteForm(@PathVariable Long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user/confirm-password";  // 비밀번호 확인 폼 페이지 반환
        }
        return "redirect:/main";  // 유저가 없을 경우 메인으로 리다이렉트
    }

    // 비밀번호 확인 후 탈퇴 처리
    @PostMapping("/{id}/confirmDelete")
    public String confirmDelete(@PathVariable Long id, @RequestParam("password") String password, Model model) {
        Optional<User> userOptional = userService.getUserById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // 비밀번호 일치 여부 확인
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                // 비밀번호가 일치하면 회원 탈퇴 처리
                userService.deleteUser(id);
                return "redirect:/users/login";  // 탈퇴 후 로그인 페이지로 리다이렉트
            } else {
                // 비밀번호가 일치하지 않으면 오류 메시지와 함께 다시 비밀번호 확인 페이지로
                model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
                model.addAttribute("user", user);
                return "user/confirm-password";
            }
        }

        // 유저가 없으면 메인 페이지로 리다이렉트
        return "redirect:/main";
    }

    @GetMapping
    public String getAllUsers(@AuthenticationPrincipal UserDetails user, Model model) {
        List<User> users = userService.getAllUsers();
        Optional<User> user1 = userService.getUserByUsername(user.getUsername());
        if (user1 != null && user1.isPresent()) {
            User user2 = user1.get();
            model.addAttribute("user2", user2);
            model.addAttribute("user2_id", user2.getId());
            log.info("User found: " + user2.getId());
            log.info("User found: " + user2.getUsername());
        }

        model.addAttribute("users", users);
        model.addAttribute("user", user);

        return "main"; // 새롭게 구성한 메인 페이지를 반환
    }

    @GetMapping("/login")
    public String showLoginUserForm() {
        return "user/login";
    }

    @GetMapping("/new")
    public String showCreateUserForm(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("user", user);
        return "user/create-user";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute User user, @RequestParam("profileImage") MultipartFile file) {
        log.info("User created: " + user + ", multipart: " + file);
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            User savedUser = userService.createUser(user);
            if (!file.isEmpty()) {
                userService.saveProfileImage(savedUser.getId(), file);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save user profile image", e);
        }
        return "redirect:/users/login"; // 사용자 생성 후 로그인 페이지로 리다이렉트
    }

    @GetMapping("/{id}/edit")
    public String showUpdateUserForm(@PathVariable Long id, Model model) {
        userService.getUserById(id).ifPresent(user -> model.addAttribute("user", user));
        return "user/update-user";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute User user, @RequestParam("profileImage") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                Optional<User> loadUser = userService.getUserById(user.getId());
                User loadedUser = loadUser.get();
                userService.deleteProfileImage(loadedUser);
                userService.saveProfileImage(user.getId(), file);
                userService.updateUser(user.getId(), user);
            } else {
                userService.updateUser(user.getId(), user);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save user profile image", e);
        }

        return "redirect:/main"; // 사용자 업데이트 후 새 메인 페이지로 리다이렉트
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/main"; // 사용자 삭제 후 새 메인 페이지로 리다이렉트
    }

    @GetMapping("/{id}/profileImage")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable Long id) {
        log.info("lsy users image 확인 ");
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent() && user.get().getProfileImageId() != null) {
            ProfileImage profileImage = userService.getProfileImage(user.get().getProfileImageId());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(profileImage.getContentType()))
                    .body(profileImage.getData());
        }
        return ResponseEntity.notFound().build();
    }
}
