package com.javaweb.Java_web.controller.admin;


import com.javaweb.Java_web.dto.response.ApiResponse;
import com.javaweb.Java_web.service.CategoryService;
import com.javaweb.Java_web.dto.request.CategoryRequest;
import com.javaweb.Java_web.entity.Category;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService ;

    //[GET] views all category ;
    @GetMapping
    public List<Category> listCategory(){
        return categoryService.listCategory();
    }

    //[GET] detail .
    @GetMapping("/detail/{categoryId}")
    public Category detailCategory(@PathVariable("categoryId") Long categoryId) {
        return categoryService.detailCategory(categoryId) ;
    }

    //[GET] update category .
    @GetMapping("/update/{categoryId}")
    public Category updateCategory(@PathVariable("categoryId") Long categoryId) {
        return categoryService.updateCategory(categoryId);
    }

    //[PUT] update category .
    @PutMapping("/update/{categoryId}")
    public Category updateCategoryPut(@PathVariable("categoryId") Long categoryId , @RequestBody CategoryRequest categoryRequest) {
       return categoryService.updateCategoryPut(categoryId,categoryRequest);
    }

    //[POST] create category .
    @PostMapping("/create")
    public ApiResponse<Category> createCategoryPost(@RequestBody @Valid CategoryRequest categoryRequest){
        ApiResponse<Category> apiResponse = new ApiResponse<>() ;
        apiResponse.setResult(categoryService.createCategoryPost(categoryRequest));
        return apiResponse ;
    }

    //[DELETE] delete category .
    @DeleteMapping("/delete/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
