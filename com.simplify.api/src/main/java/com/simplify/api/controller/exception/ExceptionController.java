package com.simplify.api.controller.exception;

import com.simplify.api.advice.exception.CAuthenticationEntryPointException;
import com.simplify.api.model.response.CommonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    @GetMapping(value = "/entrypoint")
    public CommonResult entrypointException(){
        throw new CAuthenticationEntryPointException();
    }

    @GetMapping(value = "/accessdenied")
    public CommonResult accessDeniedException(){
        throw new AccessDeniedException("");
    }
}
