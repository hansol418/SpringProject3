package com.busanit501.springproject3.lhs.service;

import com.busanit501.springproject3.lhs.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.busanit501.springproject3.lhs.entity.User;

import java.util.Optional;

@Service
@Log4j2
public class MyPageService {

    @Autowired
    private UserRepository userRepository;

    // 사용자 이름으로 사용자 정보 조회
    public Optional<User> getUserByUsername(String username) {
        // JPA User 엔티티를 반환하도록 수정
        return userRepository.findByUsername(username);
    }

    // 사용자 삭제
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            log.info("User deleted with ID: " + id);
        } else {
            log.warn("Failed to delete user: User not found with ID: " + id);
        }
    }
}