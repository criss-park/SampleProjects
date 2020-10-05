package com.simplify.api.advice.exception;

public class CEmailSigninFailedException extends RuntimeException{

    public CEmailSigninFailedException() {
        super();
    }

    public CEmailSigninFailedException(String message) {
        super(message);
    }

    public CEmailSigninFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
