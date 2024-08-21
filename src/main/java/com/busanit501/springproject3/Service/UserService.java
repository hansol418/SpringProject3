package com.busanit501.springproject3.Service;


import com.busanit501.springproject3.Entity.Token;
import com.busanit501.springproject3.Entity.User;
import com.busanit501.springproject3.Repository.TokenRepository;
import com.busanit501.springproject3.Repository.UserRepository;
import com.busanit501.springproject3.Util.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public String registerUser(String username, String password, String email) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password)); // 비밀번호 암호화
        newUser.setEmail(email);

        userRepository.save(newUser);

        // 토큰 생성
        String accessToken = jwtUtil.generateAccessToken(username);
        String refreshToken = jwtUtil.generateRefreshToken(username);

        // 토큰을 DB에 저장
        Token token = new Token();
        token.setUsername(username);
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);

        try {
            tokenRepository.save(token);
            System.out.println("Token saved successfully for user: " + username);
        } catch (Exception e) {
            System.err.println("Failed to save token for user: " + username);
            e.printStackTrace();
        }

        return accessToken;
    }

    public boolean authenticateUser(String username, String password) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}
