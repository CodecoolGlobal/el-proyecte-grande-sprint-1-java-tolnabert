package com.codecool.chilibeans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchElementException(NoSuchElementException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ElementMeantToSaveExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleSavingAlreadyExistingElement(ElementMeantToSaveExists elementMeantToSaveExists) {
        return elementMeantToSaveExists.getMessage();
    }
}
