package com.bootcamp.reactive.blog.services.impl;

import com.bootcamp.reactive.blog.core.exception.BlogLimitException;
import com.bootcamp.reactive.blog.entities.User;
import com.bootcamp.reactive.blog.repositories.UserRepository;
import com.bootcamp.reactive.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<User> save(User user) {
        return this.existsByAuthorId(user.getAuthorId()).flatMap(exist -> {
            return !exist? this.userRepository.save(user) : Mono.error(new BlogLimitException("El authorId esta asociado a otra cuenta."));
        });
    }

    @Override
    public Flux<User> findByAuthorId(String authorId) {
        return this.userRepository.findByAuthorId(authorId);
    }

    @Override
    public Mono<Boolean> existsByAuthorId(String authorId) {
        return this.userRepository.existsByAuthorId(authorId);
    }
}
