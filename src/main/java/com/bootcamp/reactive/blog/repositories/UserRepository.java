package com.bootcamp.reactive.blog.repositories;

import com.bootcamp.reactive.blog.entities.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Flux<User> findByAuthorId(String authorId);
    Mono<Boolean> existsByAuthorId(String authorId);

}
