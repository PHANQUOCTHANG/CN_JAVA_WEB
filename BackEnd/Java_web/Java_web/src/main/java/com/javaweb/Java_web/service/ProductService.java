package com.javaweb.Java_web.service;

import com.javaweb.Java_web.dto.request.ProductRequest;
import com.javaweb.Java_web.entity.Product;
import com.javaweb.Java_web.repository.CategoryRepository;
import com.javaweb.Java_web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository ;

    @Autowired
    private CategoryRepository categoryRepository ;

    //[GET] get all product
    public List<Product> listProduct(){
        return productRepository.findAll() ;
    }

    //[GET] detail product .
    public Product detailProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    //[GET] update product .
    public Product updateProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    //[PUT] update product put .
    public Product updateProductPut(Long productId , ProductRequest productRequest) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found")) ;
        product.setProductName(productRequest.getProductName());
        product.setImg(productRequest.getImg());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setStatus(productRequest.getStatus());
        product.setCategoryId(productRequest.getCategoryId()) ;
        return productRepository.save(product) ;
    }

    //[POST] create product .
    public Product createProductPost(ProductRequest productRequest) {
        Product product = new Product() ;
        product.setProductName(productRequest.getProductName());
        product.setImg(productRequest.getImg());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setStatus(productRequest.getStatus());
        product.setCategoryId(productRequest.getCategoryId()) ;
        return productRepository.save(product) ;
    }

    //[DELETE] delete product .
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }


}
