package com.balu.backend.modules.categories.controller;

import com.balu.backend.kernel.ErrorMessages;
import com.balu.backend.kernel.ResponseApi;
import com.balu.backend.kernel.SearchDto;
import com.balu.backend.modules.categories.model.Category;
import com.balu.backend.modules.categories.model.ICategoryViewPaged;
import com.balu.backend.modules.categories.model.dto.*;
import com.balu.backend.modules.categories.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
            ResponseApi<Category> responseApi=this.categoryService.saveCategory(saveCategoryDto);
            return new ResponseEntity<>(responseApi,responseApi.getStatus());
        }catch (Exception e){
            return  new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true,ErrorMessages.INTERNAL_ERROR.name()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseApi<Integer>> updateCategory(@RequestBody UpdateCategoryDto updateCategoryDto){
        try{
            ResponseApi<Integer> responseApi=this.categoryService.updateCategory(updateCategoryDto);
            return new ResponseEntity<>(responseApi,responseApi.getStatus());
        }catch (Exception e){
            System.out.println("error en put"+e);
            return  new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true,ErrorMessages.INTERNAL_ERROR.name()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/")
    public ResponseEntity<ResponseApi<Integer>> changeStatusCategory(@RequestBody ChangeStatusCategoryDto changeStatusCategoryDto){
        try{
            ResponseApi<Integer> responseApi=this.categoryService.changeStatusCategory(changeStatusCategoryDto);
            return new ResponseEntity<>(responseApi,responseApi.getStatus());
        }catch (Exception e){
            System.out.println("error en put"+e);
            return  new ResponseEntity<>(new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR,true,ErrorMessages.INTERNAL_ERROR.name()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @GetMapping("/paged")
//    public ResponseEntity<ResponseApi<Page<ICategoryViewPaged>>> getCategoryPaged(
//            @RequestParam(defaultValue = "0", required = false) int page,
//            @RequestParam(defaultValue = "10", required = false) int size,
//            @RequestParam(defaultValue = "id", required = false) String sort,
//            @RequestParam(defaultValue = "asc", required = false) String direction,
//            @RequestBody SearchCategoryDto searchCategoryDto){
//        Pageable pageable =PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(direction),sort));
//        ResponseApi<Page<ICategoryViewPaged>> responseApi = categoryService.getPaged(searchCategoryDto,pageable);
//        return new ResponseEntity<>(responseApi,responseApi.getStatus());
//    }
//

}
