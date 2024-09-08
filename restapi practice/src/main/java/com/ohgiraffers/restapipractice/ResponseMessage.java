package com.ohgiraffers.restapipractice;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseMessage {

    private int httpStatusCode;
    private String message;
    private Object results;

}