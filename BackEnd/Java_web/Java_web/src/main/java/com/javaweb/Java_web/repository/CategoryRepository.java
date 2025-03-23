package com.javaweb.Java_web.repository;

import com.javaweb.Java_web.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // get all category .
    List<Category> findAllByDeletedFalse() ;

    // get one category .
    Optional<Category> findByCategoryIdAndDeletedFalse(Long categoryId) ;
    // check name category is exists ?
    boolean existsByCategoryName(String categoryName) ;
}
