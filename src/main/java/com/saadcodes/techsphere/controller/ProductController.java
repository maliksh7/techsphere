package com.saadcodes.techsphere.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saadcodes.techsphere.dtos.ProductDto;
import com.saadcodes.techsphere.model.Product;
import com.saadcodes.techsphere.request.AddProductRequest;
import com.saadcodes.techsphere.request.ProductUpdateRequest;
import com.saadcodes.techsphere.response.ApiResponse;
import com.saadcodes.techsphere.service.product.IProductService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));
    }

    @GetMapping("/product/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            ProductDto productDto = productService.convertToDTO(product);
            return ResponseEntity.ok(new ApiResponse("Found", productDto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        Product theProduct = productService.addProduct(product);
        ProductDto productDto = productService.convertToDTO(theProduct);
        return ResponseEntity.ok(new ApiResponse("Product added successfully", productDto));
    }

    @GetMapping("/{name}/products")
    public ResponseEntity<ApiResponse> getProductsByName(@PathVariable String name) {
        List<Product> products = productService.getProductsByName(name);
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));
    }

    @GetMapping("/brand/{brand}/products")
    public ResponseEntity<ApiResponse> getProductsByBrand(@PathVariable String brand) {
        List<Product> products = productService.getProductsByBrand(brand);
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));
    }

    @GetMapping("/category/{category}/products")
    public ResponseEntity<ApiResponse> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));
    }

    @GetMapping("/by/category/{category}/brand/{brand}/products") // http://localhost:9090/api/v1/products/by/category/electronics/brand/apple/products
    public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(@PathVariable String category, @PathVariable String brand) {
        List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));

    }

    @GetMapping("/by/brand/{brand}/name/{name}/products") // http://localhost:9090/api/v1/products/by/brand/apple/name/iphone/products
    public ResponseEntity<ApiResponse> getProductsByBrandAndName(@PathVariable String brand, @PathVariable String name) {
        List<Product> products = productService.getProductsByBrandAndName(brand, name);
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));
    }

    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product deleted successfully", productId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequest product) {
        try {
            Product updatedProduct = productService.updateProduct(product, productId);
            ProductDto productDto = productService.convertToDTO(updatedProduct);
            return ResponseEntity.ok(new ApiResponse("Product updated successfully", productDto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
