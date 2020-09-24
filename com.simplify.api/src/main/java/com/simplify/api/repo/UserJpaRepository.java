package com.simplify.api.repo;

import com.simplify.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
