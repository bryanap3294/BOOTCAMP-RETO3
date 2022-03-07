package com.bootcamp.reactive.blog.services.impl;

import com.bootcamp.reactive.blog.core.exception.BlogLimitException;
import com.bootcamp.reactive.blog.entities.Comment;
import com.bootcamp.reactive.blog.repositories.CommentRepository;
import com.bootcamp.reactive.blog.services.CommentService;
import com.bootcamp.reactive.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostService postService;

    @Override
    public Mono<Comment> save(Comment comment) {
        //USUARIO PUEDE COMENTAR N VECES EN UN POST QUE ESTÉ EN ESTADO PUBLICADO
        return postService.findById(comment.getPostId()).flatMap(s -> {
            return s.getStatus().equals("PUBLICADO")? this.commentRepository.save(comment): Mono.error(new BlogLimitException("Ha superado la cantidad limite de Blog por Author"));
        });
    }
}
