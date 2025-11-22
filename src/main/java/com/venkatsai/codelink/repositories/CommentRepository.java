package com.venkatsai.codelink.repositories;

import com.venkatsai.codelink.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
