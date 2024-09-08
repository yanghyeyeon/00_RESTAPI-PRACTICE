package com.ohgiraffers.restapipractice.controller;

import com.ohgiraffers.restapipractice.ResponseMessage;
import com.ohgiraffers.restapipractice.domain.dto.CommentDto;
import com.ohgiraffers.restapipractice.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 특정 게시글에 대한 모든 댓글 조회
    @GetMapping("/post/{postId}")
    public ResponseEntity<ResponseMessage> findAllCommentsByPostId(@PathVariable long postId) {
        List<CommentDto> comments = commentService.findAllCommentsByPostId(postId);

        return ResponseEntity.ok()
                .body(new ResponseMessage(200, "댓글 목록 조회 성공", comments));
    }

    // 댓글 ID로 댓글 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<ResponseMessage> findCommentById(@PathVariable long commentId) {
        CommentDto comment = commentService.findCommentById(commentId);

        return ResponseEntity.ok()
                .body(new ResponseMessage(200, "댓글 조회 성공", comment));
    }

    // 댓글 등록
    @PostMapping
    public ResponseEntity<ResponseMessage> createComment(@RequestBody CommentDto commentDto) {
        CommentDto createdComment = commentService.createComment(commentDto);

        return ResponseEntity.ok()
                .body(new ResponseMessage(201, "댓글 등록 성공", createdComment));
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<ResponseMessage> updateComment(@PathVariable Long commentId, @RequestBody CommentDto modifyInfo) {
        CommentDto updatedComment = commentService.updateComment(commentId, modifyInfo);

        return ResponseEntity.ok()
                .body(new ResponseMessage(200, "댓글 수정 성공", updatedComment));
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseMessage> deleteComment(@PathVariable long commentId) {
        boolean isDeleted = commentService.deleteComment(commentId);

        if (isDeleted) {
            return ResponseEntity.ok()
                    .body(new ResponseMessage(204, "댓글 삭제 성공", null));
        } else {
            return ResponseEntity.ok()
                    .body(new ResponseMessage(404, "댓글 삭제 실패", null));
        }
    }
}
