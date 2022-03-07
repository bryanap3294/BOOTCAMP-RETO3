package com.bootcamp.reactive.blog.services.impl;

import com.bootcamp.reactive.blog.core.exception.AuthorExistsException;
import com.bootcamp.reactive.blog.entities.Post;
import com.bootcamp.reactive.blog.repositories.PostRepository;
import com.bootcamp.reactive.blog.services.BlogService;
import com.bootcamp.reactive.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BlogService blogService;

    @Override
    public Mono<Post> save(Post post) {

        return this.blogService.findById(post.getBlogId()).flatMap(s ->{
            return  (s.getStatus().equals("ACTIVO"))? this.postRepository.save(post) : Mono.error(new AuthorExistsException("El blog debe estar en estado activo"));
        });
        //return this.postRepository.save(post);
    }

    @Override
    public Flux<Post> findAll() {
        return this.postRepository.findAll();
    }

    @Override
    public Mono<Post> findById(String id) {
        return this.postRepository.findById(id);
    }
}
