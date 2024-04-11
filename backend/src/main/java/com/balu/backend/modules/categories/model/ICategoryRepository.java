package com.balu.backend.modules.categories.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByStatusTrue();

    Optional<Category> findByNameIgnoreCase(String name);

    @Modifying
    @Query(value = "UPDATE categories SET status = :status WHERE id = :id", nativeQuery = true)
    Integer changeStatusCategory(@Param("id") Long id, @Param("status") Boolean status);

    @Query(value = "SELECT id AS id, name AS categoryName, description AS categoryDescription, image AS categoryImage, status AS categoryStatus FROM categories WHERE LOWER(name) LIKE %:name% OR LOWER(description) LIKE %:description%", nativeQuery = true)
    Page<ICategoryViewPaged> findAllPaged(@Param("name") String name, @Param("description") String description, Pageable pageable);

    @Query(value= "SELECT * FROM categories WHERE status = true ORDER BY RAND() LIMIT 10;" , nativeQuery = true)
    List<Category> caroruselList();
}
