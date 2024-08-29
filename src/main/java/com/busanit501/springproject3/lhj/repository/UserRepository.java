package com.busanit501.springproject3.lhj.repository;


import com.busanit501.springproject3.lhj.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 추가적인 사용자 정의 쿼리 메서드를 여기에 정의할 수 있습니다.
}