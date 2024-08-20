package com.busanit501.springproject3.Service;

import com.busanit501.springproject3.Token.Tokens;
import com.busanit501.springproject3.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    // 로그인 시 호출하여 액세스 및 리프레시 토큰을 반환
    public Tokens login(String username, String password) {
        // 사용자 인증 로직 (여기서는 생략)

        // 액세스 토큰 및 리프레시 토큰 생성
        String accessToken = jwtUtil.generateAccessToken(username);
        String refreshToken = jwtUtil.generateRefreshToken(username);

        return new Tokens(accessToken, refreshToken);
    }

    // 리프레시 토큰으로 새로운 액세스 토큰을 발급
    public Tokens refreshToken(String refreshToken) {
        String username = jwtUtil.getUsernameFromToken(refreshToken);

        if (jwtUtil.validateToken(refreshToken, username)) {
            String newAccessToken = jwtUtil.generateAccessToken(username);
            return new Tokens(newAccessToken, refreshToken); // 기존 리프레시 토큰 유지
        }

        throw new RuntimeException("Invalid refresh token");
    }
}

