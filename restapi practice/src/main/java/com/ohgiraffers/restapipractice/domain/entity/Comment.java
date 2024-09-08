package com.ohgiraffers.restapipractice.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Comment(long commentId, String content, Post post) {
        this.commentId = commentId;
        this.content = content;
        this.post = post;
    }
}
