package com.busanit501.springproject3.Service;

import com.busanit501.springproject3.Entity.User;
import com.busanit501.springproject3.Repository.UserRepository;
import com.busanit501.springproject3.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(String username, String password, String email) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password)); // 비밀번호 암호화
        newUser.setEmail(email);

        userRepository.save(newUser);

        return jwtUtil.generateAccessToken(username); // 액세스 토큰 생성 및 반환
    }

    // 사용자 인증 메서드 추가
    public boolean authenticateUser(String username, String password) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}
