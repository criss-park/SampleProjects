package com.simplify.api.controller.v1;

import com.simplify.api.entity.Board;
import com.simplify.api.entity.Post;
import com.simplify.api.model.board.ParamsPost;
import com.simplify.api.model.response.CommonResult;
import com.simplify.api.model.response.ListResult;
import com.simplify.api.model.response.SingleResult;
import com.simplify.api.repo.PostJpaRepository;
import com.simplify.api.service.BoardService;
import com.simplify.api.service.ResponseService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"3. Board"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/board")
public class BoardController {

    private final BoardService boardService;
    private final ResponseService responseService;

    @ApiOperation(value = "게시판 정보 조회", notes = "게시판 정보를 조회한다.")
    @GetMapping("/{boardName}")
    public SingleResult<Board> boardInfo(@PathVariable String boardName){
        return responseService.getSingleResult(boardService.findBoard(boardName));
    }

    @ApiOperation(value = "게시판 글 리스트", notes = "게시판 게시글 리스트를 조회한다.")
    @GetMapping("/{boardName}/posts")
    public ListResult<Post> posts(@PathVariable String boardName){
        return responseService.getListResult(boardService.findPosts(boardName));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "게시판 글 작성", notes = "게시판에 글을 작성한다.")
    @PostMapping("/{boardName}")
    public SingleResult<Post> post(@PathVariable String boardName, @Valid @ModelAttribute ParamsPost paramsPost){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return responseService.getSingleResult(boardService.writePost(uid, boardName, paramsPost));
    }

    @ApiOperation(value = "게시판 글 상세", notes = "게시판에 글 상세정보를 조회한다.")
    @GetMapping("/post/{postId}")
    public SingleResult<Post> post(@PathVariable long postId){
        return responseService.getSingleResult(boardService.getPost(postId));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "게시판 글 수정", notes = "게시판의 글을 수정한다.")
    @PutMapping("/post/{postId}")
    public SingleResult<Post> post(@PathVariable long postId, @Valid @ModelAttribute ParamsPost paramsPost){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return responseService.getSingleResult(boardService.updatePost(postId, uid, paramsPost));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "게시판 글 삭제", notes = "게시판의 글을 삭제한다.")
    @DeleteMapping("/post/{postId}")
    public CommonResult deletePost(@PathVariable long postId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        boardService.deletePost(postId, uid);
        return responseService.getSuccessResult();
    }



    @ApiOperation(value = "게시판 생성(추가)", notes = "게시판을 신규 생성합니다.")
    @PostMapping("/{boardName}/new")
    public SingleResult<Board> createBoard(@PathVariable String boardName){
        return responseService.getSingleResult(boardService.createBoard(boardName));
    }
}
