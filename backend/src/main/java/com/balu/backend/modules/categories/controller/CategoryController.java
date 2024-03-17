package com.balu.backend.modules.categories.controller;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.modules.categories.model.Category;
import com.balu.backend.modules.categories.model.dto.GetCategoryListDto;
import com.balu.backend.modules.categories.model.dto.SaveCategoryDto;
import com.balu.backend.modules.categories.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<ResponseApi<List<GetCategoryListDto>>> getListCategories() {
        try {
            ResponseApi<List<GetCategoryListDto>> response = this.categoryService.getListCategories();
            return new ResponseEntity<>(response,response.getStatus());
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true, ErrorMessages.INTERNAL_ERROR.name()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseApi<Category>> saveCategory(@RequestBody SaveCategoryDto saveCategoryDto){
        try{
            ResponseApi<Category> responseApi=this.categoryService.SaveCategory(saveCategoryDto);
            return new ResponseEntity<>(responseApi,responseApi.getStatus());
        }catch (Exception e){
            return  new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true,ErrorMessages.INTERNAL_ERROR.name()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
