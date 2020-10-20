package com.simplify.api.annotation;

import com.simplify.api.model.board.ParamsPost;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ForbiddenWordCheck {
    String param() default "paramsPost.content";
    Class<?> checkClazz() default ParamsPost.class;
}
