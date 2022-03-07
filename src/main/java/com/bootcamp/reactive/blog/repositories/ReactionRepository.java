package com.bootcamp.reactive.blog.repositories;

import com.bootcamp.reactive.blog.entities.Reaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactionRepository extends ReactiveMongoRepository<Reaction, String> {

    Flux<Reaction> findByUserIdAndPostId(String userId, String postId);
    Mono<Boolean> existsByUserIdAndPostId(String userId,String postId);

}
