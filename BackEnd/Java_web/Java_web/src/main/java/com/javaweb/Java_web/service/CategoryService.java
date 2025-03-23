package com.javaweb.Java_web.service;

import com.javaweb.Java_web.dto.request.CategoryRequest;
import com.javaweb.Java_web.entity.Category;
import com.javaweb.Java_web.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository ;

    //[GET] get all category .
    public List<Category> listCategory(){
        return categoryRepository.findAllByDeletedFalse() ;
    }

    //[GET] detail category .
    public Category detailCategory(Long categoryId) {
        return categoryRepository.findByCategoryIdAndDeletedFalse(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
    }
    
    //[GET] update category .
    public Category updateCategory(Long categoryId) {
        return categoryRepository.findByCategoryIdAndDeletedFalse(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
    }
    
    //[PUT] update category .
    public Category updateCategoryPut(Long categoryId , CategoryRequest categoryRequest) {
        Category category = categoryRepository.findByCategoryIdAndDeletedFalse(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setCategoryName(categoryRequest.getCategoryName());
        return categoryRepository.save(category) ;
    }

    //[POST] create category .
    public Category createCategoryPost(CategoryRequest categoryRequest) {

        if (categoryRepository.existsByCategoryName(categoryRequest.getCategoryName()))
            throw new RuntimeException("categoryName existed") ;

        Category category = new Category() ;
        category.setCategoryName(categoryRequest.getCategoryName());
        return categoryRepository.save(category) ;
    }
    
    //[DELETE] delete category ;
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findByCategoryIdAndDeletedFalse(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setDeleted(true);
        categoryRepository.save(category) ;
    }


}
