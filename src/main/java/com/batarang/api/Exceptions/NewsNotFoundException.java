package com.batarang.api.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NewsNotFoundException extends RuntimeException {



    public NewsNotFoundException(){
        super("There is no news with id");
    }


}
