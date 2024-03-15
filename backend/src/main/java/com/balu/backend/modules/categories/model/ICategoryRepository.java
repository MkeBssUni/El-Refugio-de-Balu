package com.balu.backend.modules.categories.model;

import com.balu.backend.modules.categories.model.dto.GetCategoryListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ICategoryRepository  extends JpaRepository<Category,Long> {
    @Query(value="SELECT id,name  FROM categories WHERE status=true", nativeQuery = true)
    List<GetCategoryListDto>getListCategories();
}
