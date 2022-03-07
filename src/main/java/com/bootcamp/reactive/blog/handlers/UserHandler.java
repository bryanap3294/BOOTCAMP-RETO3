package com.bootcamp.reactive.blog.handlers;

import com.bootcamp.reactive.blog.entities.User;
import com.bootcamp.reactive.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class UserHandler {

    @Autowired
    private UserService userService;

    public Mono<ServerResponse> save(ServerRequest request){

        var userInput= request.bodyToMono(User.class);
        return userInput
                .flatMap(user-> this.userService.save(user))
                .flatMap(a-> ServerResponse
                        .ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(a), User.class));
    }
}
