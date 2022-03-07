package com.bootcamp.reactive.blog.services;

import com.bootcamp.reactive.blog.entities.Reaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactionService {

    Mono<Reaction> save(Reaction reaction);
    Flux<Reaction> findByUserIdAndPostId(String userId, String postId);
}
