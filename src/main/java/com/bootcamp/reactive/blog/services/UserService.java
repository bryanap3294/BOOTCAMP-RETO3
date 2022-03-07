package com.bootcamp.reactive.blog.services;

import com.bootcamp.reactive.blog.entities.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> save(User user);
    Flux<User> findByAuthorId(String authorId);
    Mono<Boolean> existsByAuthorId(String authorId);

}
