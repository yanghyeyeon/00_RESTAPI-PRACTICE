package com.ohgiraffers.restapipractice.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentDto {
    private long commentId;
    private String content;
    private long postId;
}
