package com.venkatsai.codelink.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.venkatsai.codelink.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User,Long> {
}
