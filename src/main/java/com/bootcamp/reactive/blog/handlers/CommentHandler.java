package com.bootcamp.reactive.blog.handlers;

import com.bootcamp.reactive.blog.entities.Comment;
import com.bootcamp.reactive.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class CommentHandler {

    @Autowired
    private CommentService commentService;


    public Mono<ServerResponse> save(ServerRequest request){

        var commentInput= request.bodyToMono(Comment.class);
        return commentInput
                .flatMap(comment-> this.commentService.save(comment))
                .flatMap(a-> ServerResponse
                        .ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(a), Comment.class));
    }

}
