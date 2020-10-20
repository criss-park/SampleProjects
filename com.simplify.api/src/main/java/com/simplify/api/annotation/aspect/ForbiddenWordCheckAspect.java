package com.simplify.api.annotation.aspect;

import com.simplify.api.advice.exception.CForbiddenWordException;
import com.simplify.api.annotation.ForbiddenWordCheck;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Aspect
@Component
public class ForbiddenWordCheckAspect {

    @Before(value = "@annotation(forbiddenWordCheck)")
    public void forbiddenWordCheck(JoinPoint joinPoint, ForbiddenWordCheck forbiddenWordCheck) throws Throwable {

        log.debug(this.getClass().getSimpleName() + " - forbiddenWordCheck()");

        String[] param = forbiddenWordCheck.param().split("\\.");
        String paramName;
        String fieldName = "";
        if (param.length == 2) {
            paramName = param[0];
            fieldName = param[1];
        } else {
            paramName = forbiddenWordCheck.param();
        }

        Integer parameterIdx = getParameterIdx(joinPoint, paramName);
        if (parameterIdx == -1) {
            throw new IllegalArgumentException();
        }

        String checkWord;
        if (StringUtils.isNotEmpty(fieldName)) {
            Class<?> clazz = forbiddenWordCheck.checkClazz();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            checkWord = (String) field.get(joinPoint.getArgs()[parameterIdx]);
        }else{
            checkWord = (String) joinPoint.getArgs()[parameterIdx];
        }

        checkForbiddenWord(checkWord);
    }

    private Integer getParameterIdx(JoinPoint joinPoint, String paramName) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            String pName = parameterNames[i];
            if (pName.equals(paramName)) {
                return i;
            }
        }
        return -1;
    }

    private void checkForbiddenWord(String content) {
        List<String> forbiddenWords = Arrays.asList("개새끼", "쌍년", "씨발");
        Optional<String> forbiddenWord = forbiddenWords.stream().filter(content::contains).findFirst();
        if (forbiddenWord.isPresent()) {

            log.debug(this.getClass().getSimpleName() + " " + forbiddenWord.get());

            throw new CForbiddenWordException(forbiddenWord.get());
        }
    }

}
