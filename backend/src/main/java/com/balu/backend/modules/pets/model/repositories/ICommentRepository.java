package com.balu.backend.modules.pets.model.repositories;

import com.balu.backend.modules.pets.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
}
