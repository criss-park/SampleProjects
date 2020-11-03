package com.simplify.multidsspringboot.dao.user;

import com.simplify.multidsspringboot.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
