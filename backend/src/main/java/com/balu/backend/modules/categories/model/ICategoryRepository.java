package com.balu.backend.modules.categories.model;

import com.balu.backend.modules.categories.model.dto.GetCategoryListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ICategoryRepository  extends JpaRepository<Category,Long> {
    List<Category> findByStatusTrue();
    Category findByName(String name);

    @Modifying
    @Query(value = "UPDATE categories SET name = :name, description = :description, image = :image WHERE id = :id", nativeQuery = true)
    Integer updateCategory(@Param("id") Long id, @Param("name") String name, @Param("description") String description, @Param("image") String image);
}
