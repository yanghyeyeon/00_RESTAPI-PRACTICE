package com.ohgiraffers.restapipractice.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PostDto {

    private long PostId;
    private String PostTitle;
    private String PostContent;

}
