package com.ohgiraffers.restapipractice.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;
    private String postTitle;
    private String postContent;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comment;

    @Builder
    public Post(long postId, String postTitle, String postContent, List<Comment> comment) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.comment = comment;
    }
}
