package com.saadcodes.techsphere.dtos;

import com.saadcodes.techsphere.model.Category;
import com.saadcodes.techsphere.model.Image;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;
    private Category category;
    private List<ImageDto> images;  // List of ImageDto objects, not Image entities (which means relevant data of Image entity will be fetched)
}
