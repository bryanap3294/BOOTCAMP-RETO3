package com.bootcamp.reactive.blog.services.impl;

import com.bootcamp.reactive.blog.core.exception.AuthorExistsException;
import com.bootcamp.reactive.blog.entities.Reaction;
import com.bootcamp.reactive.blog.repositories.ReactionRepository;
import com.bootcamp.reactive.blog.services.PostService;
import com.bootcamp.reactive.blog.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactionServiceImpl implements ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private PostService postService;

    @Override
    public Mono<Reaction> save(Reaction reaction) {
        //USUARIO SOLO PUEDE TENER UNA REACCION POR CADA POST
        return reactionRepository.existsByUserIdAndPostId(reaction.getUserId(), reaction.getPostId()).flatMap(exist ->{
            return !exist? this.reactionRepository.save(reaction) : Mono.error(new AuthorExistsException("Solo se puede reaccionar una ves a un post"));
        });
    }

    @Override
    public Flux<Reaction> findByUserIdAndPostId(String userId, String postId) {
        return this.reactionRepository.findByUserIdAndPostId(userId, postId);
    }
}
