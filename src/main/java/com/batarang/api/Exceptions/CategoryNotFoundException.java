package com.batarang.api.Exceptions;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException() {
        super("There is no category with id");
    }
}
