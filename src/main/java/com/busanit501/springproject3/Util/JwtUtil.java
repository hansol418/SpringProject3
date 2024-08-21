package com.busanit501.springproject3.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 사용자명 추출 메서드
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 만료 날짜 추출 메서드
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 특정 클레임 추출 메서드
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 모든 클레임 추출 메서드
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 토큰 만료 여부 확인 메서드
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 액세스 토큰 생성 메서드
    public String generateAccessToken(String username) {
        return createToken(username, 1000 * 60 * 60); // 액세스 토큰 1시간 유효
    }

    // 리프레시 토큰 생성 메서드
    public String generateRefreshToken(String username) {
        return createToken(username, 1000 * 60 * 60 * 24 * 7); // 리프레시 토큰 7일 유효
    }

    // 토큰 생성 메서드
    private String createToken(String username, long expirationTime) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SECRET_KEY)
                .compact();
    }

    // 토큰 유효성 확인 메서드
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
