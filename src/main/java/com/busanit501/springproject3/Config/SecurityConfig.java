package com.busanit501.springproject3.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable // CSRF 보호 비활성화
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/**").permitAll() // 인증이 필요 없는 API 엔드포인트
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )
                .formLogin(formLogin -> formLogin
                        .loginProcessingUrl("/perform_login") // 로그인 처리 URL 설정
                        .defaultSuccessUrl("/main", true) // 로그인 성공 시 리다이렉트 URL 설정
                        .permitAll() // 로그인 페이지 접근 허용
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 URL 설정
                        .logoutSuccessUrl("/login?logout") // 로그아웃 성공 시 리다이렉트 URL 설정
                        .permitAll() // 로그아웃 접근 허용
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
