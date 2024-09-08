package com.ohgiraffers.restapipractice.repository;

import com.ohgiraffers.restapipractice.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
