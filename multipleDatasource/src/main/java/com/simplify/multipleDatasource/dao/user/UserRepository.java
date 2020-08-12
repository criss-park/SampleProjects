package com.simplify.multipleDatasource.dao.user;

import com.simplify.multipleDatasource.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
