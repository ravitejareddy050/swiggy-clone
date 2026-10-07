package com.example.swiggyauthservice.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class Phonenumberexception extends RuntimeException {
    public Phonenumberexception(String message) {
        super(message);
    }

}
