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
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(new ApiResponse("Success", categories));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category) {
        Category theCategory = categoryService.addCategory(category);
        return ResponseEntity.ok(new ApiResponse("Category added successfully", theCategory));
    }

    @GetMapping("/category/{id}/category")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findCategoryById(id);
        return ResponseEntity.ok(new ApiResponse("Success", category));
    }

    @GetMapping("/category/{name}/category")
    public  ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name){
        Category category = categoryService.findCategoryByName(name);
        return ResponseEntity.ok(new ApiResponse("Success", category));
    }

    @DeleteMapping("/category/{id}/delete")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse("Category deleted successfully", null));
    }

    @PutMapping("/category/{id}/update")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(category, id);
        return ResponseEntity.ok(new ApiResponse("Category updated successfully", updatedCategory));
    }
}
