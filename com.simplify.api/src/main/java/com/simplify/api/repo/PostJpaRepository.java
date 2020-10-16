package com.simplify.api.repo;

import com.simplify.api.entity.Board;
import com.simplify.api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

    List<Post> findByBoard(Board board);
}
