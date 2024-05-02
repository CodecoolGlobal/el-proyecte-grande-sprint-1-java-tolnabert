package com.codecool.chilibeans.exception;

public class ElementMeantToSaveExists extends RuntimeException {

    public ElementMeantToSaveExists(Object o) {
        super("Element " + o + " is already exists in database.");
    }
}
