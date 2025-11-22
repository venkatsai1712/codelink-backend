package com.venkatsai.codelink.repositories;

import com.venkatsai.codelink.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {
}
