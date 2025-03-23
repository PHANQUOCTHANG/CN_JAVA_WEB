package com.javaweb.Java_web.controller.admin;

import com.javaweb.Java_web.dto.request.ProductRequest;
import com.javaweb.Java_web.entity.Product;
import com.javaweb.Java_web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService ;

    //[GET] get all product .
    @GetMapping
    public List<Product> listProduct() {
        return productService.listProduct() ;
    }

    //[GET] detail .
    @GetMapping("/detail/{productId}")
    public Product detailProduct(@PathVariable("productId") Long productId) {
        return productService.detailProduct(productId) ;
    }

    //[GET] update category .
    @GetMapping("/update/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId) {
        return productService.updateProduct(productId);
    }

    //[PUT] update category .
    @PutMapping("/update/{productId}")
    public Product updateProductPut(@PathVariable("productId") Long productId , @RequestBody ProductRequest productRequest) {
        return productService.updateProductPut(productId,productRequest);
    }

    //[POST] create product .
    @PostMapping("/create")
    public Product createProductPost(@RequestBody ProductRequest productRequest) {
        return productService.createProductPost(productRequest) ;
    }

    //[DELETE] delete product .
    public void deleteProduct(@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
    }
}
