package com.balu.backend.modules.pets.model.repositories;

import com.balu.backend.modules.pets.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select pc.comment as comment, lower(r.name) as userRole from pet_comments pc " +
                        "inner join users u on pc.user_id = u.id " +
                        "inner join roles r on u.role_id = r.id " +
                        "where pc.pet_id = ?1 order by pc.id desc", nativeQuery = true)
    List<Comment> findByPet(Long petId);
}
