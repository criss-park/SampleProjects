package com.simplify.api.controller.v1;

import com.simplify.api.entity.User;
import com.simplify.api.repo.UserJpaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. User Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class UserController {

    private final UserJpaRepository userJpaRepository;

    @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회한다.")
    @GetMapping(value = "/user")
    public List<User> findAllUser(){
        return userJpaRepository.findAll();
    }

    @ApiOperation(value = "회원 입력", notes = "회원을 입력한다.")
    @PostMapping(value = "/user")
    public User save(
            @ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원이름", required = true) @RequestParam String name){
        User newUser = User.builder().uid(uid).name(name).build();
        return userJpaRepository.save(newUser);
    }

}
