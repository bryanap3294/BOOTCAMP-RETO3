package com.bootcamp.reactive.blog.services;

import com.bootcamp.reactive.blog.entities.Comment;
import reactor.core.publisher.Mono;

public interface CommentService {

    Mono<Comment> save(Comment comment);

}
