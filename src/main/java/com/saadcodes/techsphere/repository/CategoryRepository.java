package com.saadcodes.techsphere.repository;

import com.saadcodes.techsphere.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    boolean existsByName(String name);
}
