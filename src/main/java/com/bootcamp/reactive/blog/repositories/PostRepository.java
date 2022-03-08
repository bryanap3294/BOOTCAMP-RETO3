package com.bootcamp.reactive.blog.repositories;

import com.bootcamp.reactive.blog.entities.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Calendar;

@Repository
public interface PostRepository extends ReactiveMongoRepository<Post, String> {

    Flux<Post> findByBlogId(String blogId);
    Flux<Post> findByBlogIdAndDate(String blogId, Calendar date);
    Mono<Boolean> existsByBlogIdAndDate(String blogId, Calendar date);

}
