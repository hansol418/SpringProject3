package com.busanit501.springproject3.lhs.repository;


import com.busanit501.springproject3.lhs.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
