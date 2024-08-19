package com.busanit501.springproject3.Repository;

import com.busanit501.springproject3.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
