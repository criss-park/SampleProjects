package com.simplify.api.controller.v1;

import com.simplify.api.advice.exception.CEmailSigninFailedException;
import com.simplify.api.config.security.JwtTokenProvider;
import com.simplify.api.entity.User;
import com.simplify.api.model.response.CommonResult;
import com.simplify.api.model.response.SingleResult;
import com.simplify.api.repo.UserJpaRepository;
import com.simplify.api.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignController {

    private final UserJpaRepository userJpaRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public SingleResult<String> signin(@ApiParam(value = "회원 ID : 이메일", required = true) @RequestParam String id,
                                       @ApiParam(value = "비밀번호", required = true) @RequestParam String password){
        User user = userJpaRepository.findByUid(id).orElseThrow(CEmailSigninFailedException::new);
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new CEmailSigninFailedException();
        }
        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));
    }

    @ApiOperation(value = "가입", notes = "회원 가입을 한다.")
    @PostMapping(value = "/signup")
    public CommonResult signup(@ApiParam(value = "회원 ID : 이메일", required = true) @RequestParam String id,
                               @ApiParam(value = "이름", required = true) @RequestParam String name,
                               @ApiParam(value = "비밀번호", required = true) @RequestParam String password){
        userJpaRepository.save(User.builder()
                .uid(id)
                .name(name)
                .password(passwordEncoder.encode(password))
                //.roles(Collections.singletonList("ROLE_USER"))
                .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                .build());
        return responseService.getSuccessResult();
    }
}
