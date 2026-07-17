package com.saadcodes.techsphere.controller;


import com.saadcodes.techsphere.model.Category;
import com.saadcodes.techsphere.response.ApiResponse;
import com.saadcodes.techsphere.service.category.CategoryService;
import com.saadcodes.techsphere.service.category.ICategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {
        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Success", categories));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error retrieving categories: ", e.getMessage()));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category) {
        try {
            Category theCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("Category added successfully", theCategory));
        } catch (Exception e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Error adding category: ", e.getMessage()));
        }
    }

    @GetMapping("/category/{id}/category")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        try {
            Category category = categoryService.findCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Success", category));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Error retrieving category: ", e.getMessage()));
        }
    }

    @GetMapping("/category/{name}/category")
    public  ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name){
        try {
            Category category = categoryService.findCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("Success", category));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Error retrieving category: ", e.getMessage()));
        }
    }

    @DeleteMapping("/category/{id}/delete")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok(new ApiResponse("Category deleted successfully", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Error retrieving category: ", e.getMessage()));
        }
    }

    @PutMapping("/category/{id}/update")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        try {
            Category updatedCategory = categoryService.updateCategory(category, id);
            return ResponseEntity.ok(new ApiResponse("Category updated successfully", updatedCategory));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Error retrieving category: ", e.getMessage()));
        }
    }
}
