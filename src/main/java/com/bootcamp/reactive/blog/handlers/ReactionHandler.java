package com.bootcamp.reactive.blog.handlers;

import com.bootcamp.reactive.blog.entities.Reaction;
import com.bootcamp.reactive.blog.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class ReactionHandler {

    @Autowired
    private ReactionService reactionService;


    public Mono<ServerResponse> save(ServerRequest request){

        var reactionInput= request.bodyToMono(Reaction.class);
        return reactionInput
                .flatMap(reaction-> this.reactionService.save(reaction))
                .flatMap(a-> ServerResponse
                        .ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(a), Reaction.class));
    }

}
