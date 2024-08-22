package com.busanit501.springproject3.repository;


import com.busanit501.springproject3.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
