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
        // 중복 사용자 확인
        Optional<User> existingUser = Optional.ofNullable(userRepository.findByUsername(username));
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists with username: " + username);
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password)); // 비밀번호 암호화
        newUser.setEmail(email);

        userRepository.save(newUser);

        // JWT 생성 및 반환
        return jwtUtil.generateToken(username);
    }
}
