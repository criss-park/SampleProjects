package com.simplify.api.controller.v1;

import com.simplify.api.advice.exception.CUserNotFoundException;
import com.simplify.api.entity.User;
import com.simplify.api.model.response.CommonResult;
import com.simplify.api.model.response.ListResult;
import com.simplify.api.model.response.SingleResult;
import com.simplify.api.repo.UserJpaRepository;
import com.simplify.api.service.ResponseService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

//@PreAuthorize("hasRole('ROLE_USER')")
//@Secured("ROLE_USER")
@Api(tags = {"1. User Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class UserController {

    private final UserJpaRepository userJpaRepository;
    private final ResponseService responseService;

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다.")
    @GetMapping(value = "/users")
    public ListResult<User>  findAllUser(){
        return responseService.getListResult(userJpaRepository.findAll());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 단건 조회", notes = "userId로 회원을 조회한다.")
    @GetMapping(value = "/user")
    public SingleResult<User> findUserById(
            @ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) throws Exception {
        //return responseService.getSingleResult(userJpaRepository.findById(msrl).orElse(null));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        return responseService.getSingleResult(userJpaRepository.findByUid(id).orElseThrow(CUserNotFoundException::new));
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access token", required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "회원 입력", notes = "회원을 입력한다.")
//    @PostMapping(value = "/user")
//    public SingleResult<User> save(
//            @ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
//            @ApiParam(value = "회원이름", required = true) @RequestParam String name){
//        User newUser = User.builder().uid(uid).name(name).build();
//        return responseService.getSingleResult(userJpaRepository.save(newUser));
//    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 수정", notes = "회원 정보를 수정한다.")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(
            @ApiParam(value = "회원번호", required = true) @RequestParam Long msrl,
            @ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원 이름", required = true) @RequestParam String name){
        User modifyUser = User.builder().msrl(msrl).uid(uid).name(name).build();
        return responseService.getSingleResult(userJpaRepository.save(modifyUser));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access token", required = true, dataType = "String", paramType = "header")
    })
    @DeleteMapping(value = "/user/{msrl}")
    public CommonResult delete(
            @ApiParam(value = "회원번호", required = true) @PathVariable Long msrl){
        userJpaRepository.deleteById(msrl);
        return responseService.getSuccessResult();
    }


}
