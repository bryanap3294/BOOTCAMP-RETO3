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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BlogService blogService;

    @Override
    public Mono<Post> save(Post post) {
        Date currentDate = new Date();
        return this.blogService.findById(post.getBlogId()).flatMap(s ->{
            //post.setDate(currentDate);
            if(s.getStatus().equals("ACTIVO")){
                return postRepository.findByBlogId(post.getBlogId()).filter(p -> {
                    LocalDate localDate = p.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate currentDay = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    return localDate.isEqual(currentDay);
                }).hasElements().flatMap(w->{
                    post.setDate(currentDate);
                    return !w? this.postRepository.save(post) : Mono.error(new AuthorExistsException("Solo un POST por dia"));
                });
            }else{
                return Mono.error(new AuthorExistsException("El blog debe estar en estado activo"));
            }
        });
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
