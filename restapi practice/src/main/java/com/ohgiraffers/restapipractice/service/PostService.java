package com.ohgiraffers.restapipractice.service;

import com.ohgiraffers.restapipractice.domain.dto.PostDto;
import com.ohgiraffers.restapipractice.domain.entity.Post;
import com.ohgiraffers.restapipractice.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository repo;

    public PostService(PostRepository postRepository) {
        this.repo = postRepository;
    }

    public List<PostDto> findAllPosts() {
        return repo.findAll().stream().map(entity ->
                        PostDto.builder()
                                .PostId(entity.getPostId())
                                .PostTitle(entity.getPostTitle())
                                .PostContent(entity.getPostContent())
                                .build())
                .collect(Collectors.toList()
                );
    }

    public PostDto findPostById(long postId) {

        Post postEntity = repo.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Post not found with id " + postId));

        return PostDto.builder()
                .PostId(postEntity.getPostId())
                .PostTitle(postEntity.getPostTitle())
                .PostContent(postEntity.getPostContent())
                .build();
    }

    public PostDto createPost(PostDto newPost) {

        Post post = Post.builder()
                .postTitle(newPost.getPostTitle())
                .postContent(newPost.getPostContent())
                .build();

        Post savedPost = repo.save(post);

        return new PostDto(savedPost.getPostId(), savedPost.getPostTitle(), savedPost.getPostContent());
    }

    public PostDto updatePost(Long postId, PostDto modifyInfo) {

        Post postEntity = repo.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Post not found with postId " + postId));

        postEntity.setPostTitle(modifyInfo.getPostTitle());
        postEntity.setPostContent(modifyInfo.getPostContent());

        Post updatedPost = repo.save(postEntity);

        return new PostDto(updatedPost.getPostId(), updatedPost.getPostTitle(), updatedPost.getPostContent());
    }

    public boolean deletePost(long postNo) {
        try {
            if (repo.existsById(postNo)) {
                repo.deleteById(postNo);
                return true; // 게시글 삭제 성공
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}