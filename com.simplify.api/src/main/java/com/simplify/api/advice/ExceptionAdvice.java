package com.simplify.api.advice;

import com.simplify.api.advice.exception.CUserNotFoundException;
import com.simplify.api.model.response.CommonResult;
import com.simplify.api.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    private final ResponseService responseService;

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest httpServletRequest, Exception exception){
        return responseService.getFailResult(Integer.valueOf(getMessage("unknown.code")), getMessage("unknown.msg"));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CUserNotFoundException.class)
    protected CommonResult userNotFoundException(HttpServletRequest httpServletRequest, Exception exception){

        //log.debug(getMessage("userNotFound.code"));
        log.debug(messageSource.getMessage("userNotFound.code", null, Locale.ENGLISH));

        return responseService.getFailResult(Integer.valueOf(getMessage("userNotFound.code")), getMessage("userNotFound.msg"));
    }

    private String getMessage(String code){
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
