package com.simplify.api.controller.v1;

import com.simplify.api.advice.exception.CUserNotFoundException;
import com.simplify.api.entity.User;
import com.simplify.api.model.response.CommonResult;
import com.simplify.api.model.response.ListResult;
import com.simplify.api.model.response.SingleResult;
import com.simplify.api.repo.UserJpaRepository;
import com.simplify.api.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. User Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class UserController {

    private final UserJpaRepository userJpaRepository;
    private final ResponseService responseService;

    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다.")
    @GetMapping(value = "/users")
    public ListResult<User>  findAllUser(){
        return responseService.getListResult(userJpaRepository.findAll());
    }

    @ApiOperation(value = "회원 단건 조회", notes = "userId로 회원을 조회한다.")
    @GetMapping(value = "/user/{msrl}")
    public SingleResult<User> findUserById(
            @ApiParam(value = "회원 ID", required = true) @PathVariable Long msrl,
            @ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) throws Exception {
        //return responseService.getSingleResult(userJpaRepository.findById(msrl).orElse(null));
        return responseService.getSingleResult(userJpaRepository.findById(msrl).orElseThrow(CUserNotFoundException::new));
    }

    @ApiOperation(value = "회원 입력", notes = "회원을 입력한다.")
    @PostMapping(value = "/user")
    public SingleResult<User> save(
            @ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원이름", required = true) @RequestParam String name){
        User newUser = User.builder().uid(uid).name(name).build();
        return responseService.getSingleResult(userJpaRepository.save(newUser));
    }

    @ApiOperation(value = "회원 수정", notes = "회원 정보를 수정한다.")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(
            @ApiParam(value = "회원번호", required = true) @RequestParam Long msrl,
            @ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원 이름", required = true) @RequestParam String name){
        User modifyUser = User.builder().msrl(msrl).uid(uid).name(name).build();
        return responseService.getSingleResult(userJpaRepository.save(modifyUser));
    }

    @DeleteMapping(value = "/user/{msrl}")
    public CommonResult delete(
            @ApiParam(value = "회원번호", required = true) @PathVariable Long msrl){
        userJpaRepository.deleteById(msrl);
        return responseService.getSuccessResult();
    }


}
