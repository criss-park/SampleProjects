package com.simplify.api.service;

import com.simplify.api.advice.exception.CNotOwnerException;
import com.simplify.api.advice.exception.CResourceNotExistException;
import com.simplify.api.advice.exception.CUserNotFoundException;
import com.simplify.api.entity.Board;
import com.simplify.api.entity.Post;
import com.simplify.api.entity.User;
import com.simplify.api.model.board.ParamsPost;
import com.simplify.api.repo.BoardJpaRepository;
import com.simplify.api.repo.PostJpaRepository;
import com.simplify.api.repo.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardJpaRepository boardJpaRepository;
    private final PostJpaRepository postJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public Board findBoard(String boardName){
        return Optional.ofNullable(boardJpaRepository.findByName(boardName)).orElseThrow(CResourceNotExistException::new);
    }

    public List<Post> findPosts(String boardName){
        return postJpaRepository.findByBoard(findBoard(boardName));
    }

    public Post getPost(long postId){
        return postJpaRepository.findById(postId).orElseThrow(CResourceNotExistException::new);
    }

    public Post writePost(String uid, String boardName, ParamsPost paramsPost){
        Board board = findBoard(boardName);
        Post post = new Post(paramsPost.getTitle()
                , paramsPost.getAuthor()
                , paramsPost.getContent()
                , board
                , userJpaRepository.findByUid(uid).orElseThrow(CUserNotFoundException::new));
        return postJpaRepository.save(post);
    }

    public Post updatePost(long postId, String uid, ParamsPost paramsPost) {
        Post post = getPost(postId);
        User user = post.getUser();
        if (!uid.equals(user.getUid())) {
            throw new CNotOwnerException();
        }
        return post.setUpdate(paramsPost.getAuthor(), paramsPost.getTitle(), paramsPost.getContent());
    }

    public boolean deletePost(long postId, String uid){
        Post post = getPost(postId);
        User user = post.getUser();
        if (!uid.equals(user.getUid())) {
            throw new CNotOwnerException();
        }
        postJpaRepository.delete(post);
        return true;
    }

    public Board createBoard(String boardName) {
        Board board = new Board(boardName);
        return boardJpaRepository.save(board);
    }
}
