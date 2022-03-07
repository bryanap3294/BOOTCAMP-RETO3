package com.bootcamp.reactive.blog.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogLimitException extends RuntimeException{

    private HttpStatus status  = HttpStatus.MULTIPLE_CHOICES;
    private String message;

    public BlogLimitException(String message){
        this.message = message;
    }

}
