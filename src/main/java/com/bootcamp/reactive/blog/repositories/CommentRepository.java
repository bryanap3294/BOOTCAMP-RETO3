package com.bootcamp.reactive.blog.repositories;

import com.bootcamp.reactive.blog.entities.Comment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {



}
