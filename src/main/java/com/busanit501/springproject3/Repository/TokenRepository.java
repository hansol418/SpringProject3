package com.busanit501.springproject3.Repository;

import com.busanit501.springproject3.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
