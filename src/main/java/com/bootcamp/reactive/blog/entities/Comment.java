package com.bootcamp.reactive.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "comment")
public class Comment {

    @Id
    private String id;
    private Date date;
    private String name;
    private String status;
    private String comment;
    private String postId;
    private String userId;
}
