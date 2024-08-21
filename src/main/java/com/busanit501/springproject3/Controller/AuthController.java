package com.busanit501.springproject3.Controller;

import com.busanit501.springproject3.Service.UserService;
import com.busanit501.springproject3.Util.JwtUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // 사용자 등록
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String token = userService.registerUser(request.getUsername(), request.getPassword(), request.getEmail());
        return ResponseEntity.ok(token);
    }

    // 로그인 처리
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // 실제 로그인 검증 로직은 UserService에 구현되어 있어야 합니다.
        if (userService.authenticateUser(request.getUsername(), request.getPassword())) {
            String accessToken = jwtUtil.generateAccessToken(request.getUsername());
            String refreshToken = jwtUtil.generateRefreshToken(request.getUsername());
            return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken));
        } else {
            return ResponseEntity.status(401).body(null); // 인증 실패 시 401 응답
        }
    }

    // 리프레시 토큰으로 새 액세스 토큰 발급
    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        // 리프레시 토큰 검증
        if (jwtUtil.validateToken(request.getRefreshToken(), request.getUsername())) {
            String newAccessToken = jwtUtil.generateAccessToken(request.getUsername());
            return ResponseEntity.ok(new LoginResponse(newAccessToken, request.getRefreshToken()));
        } else {
            return ResponseEntity.status(401).body(null); // 리프레시 토큰이 유효하지 않으면 401 응답
        }
    }
}

@Data
class RegisterRequest {
    private String username;
    private String password;
    private String email;

    // Getters and Setters
}

@Data
class LoginRequest {
    private String username;
    private String password;

    // Getters and Setters
}

@Data
class LoginResponse {
    private String accessToken;
    private String refreshToken;

    public LoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    // Getters and Setters
}

@Data
class RefreshTokenRequest {
    private String refreshToken;
    private String username;

    // Getters and Setters
}
