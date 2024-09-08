package com.ohgiraffers.restapipractice.service;

import com.ohgiraffers.restapipractice.domain.dto.CommentDto;
import com.ohgiraffers.restapipractice.domain.dto.PostDto;
import com.ohgiraffers.restapipractice.domain.entity.Comment;
import com.ohgiraffers.restapipractice.domain.entity.Post;
import com.ohgiraffers.restapipractice.repository.CommentRepository;
import com.ohgiraffers.restapipractice.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<CommentDto> findAllCommentsByPostId(long postId) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getPost().getPostId() == postId)
                .map(comment -> CommentDto.builder()
                        .commentId(comment.getCommentId())
                        .content(comment.getContent())
                        .postId(comment.getPost().getPostId())
                        .build())
                .collect(Collectors.toList());
    }

    public CommentDto findCommentById(long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("Comment not found with id " + commentId));

        return CommentDto.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .postId(comment.getPost().getPostId())
                .build();
    }

    public CommentDto createComment(CommentDto newCommentDto) {
        Post post = postRepository.findById(newCommentDto.getPostId())
                .orElseThrow(() -> new NoSuchElementException("Post not found with id " + newCommentDto.getPostId()));

        Comment comment = Comment.builder()
                .content(newCommentDto.getContent())
                .post(post)
                .build();

        Comment savedComment = commentRepository.save(comment);

        return CommentDto.builder()
                .commentId(savedComment.getCommentId())
                .content(savedComment.getContent())
                .postId(savedComment.getPost().getPostId())
                .build();
    }

    public CommentDto updateComment(Long commentId, CommentDto modifyInfo) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("Comment not found with id " + commentId));

        comment.setContent(modifyInfo.getContent());

        commentRepository.save(comment);

        return CommentDto.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .postId(comment.getPost().getPostId())
                .build();
    }

    public boolean deleteComment(long commentId) {
        try {
            if (commentRepository.existsById(commentId)) {
                commentRepository.deleteById(commentId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
