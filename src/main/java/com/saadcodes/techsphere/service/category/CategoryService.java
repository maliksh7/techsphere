package com.saadcodes.techsphere.service.category;

import com.saadcodes.techsphere.model.Category;
import com.saadcodes.techsphere.repository.CategoryRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor                                        // This annotation generates a constructor with required arguments (final fields)
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category)
                .filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository::save)
                .orElseThrow(() -> new EntityExistsException(
                        "Category with name " + category.getName() + " already exists."));
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        return Optional.ofNullable(findCategoryById(categoryId))
                .map(oldCategory -> {
                    oldCategory.setName(category.getName());
                    return categoryRepository.save(oldCategory);
                }).orElseThrow(() ->
                        new EntityNotFoundException("Category not found with id: " + categoryId));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.findById(categoryId).ifPresentOrElse(categoryRepository :: delete, () -> {
            throw new EntityNotFoundException("Category not found with id: " + categoryId);
        });
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new EntityNotFoundException("Category not found with id: " + categoryId));
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
}
