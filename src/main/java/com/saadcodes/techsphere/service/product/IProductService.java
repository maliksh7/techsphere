package com.saadcodes.techsphere.service.product;

import com.saadcodes.techsphere.dtos.ProductDto;
import com.saadcodes.techsphere.model.Product;
import com.saadcodes.techsphere.request.AddProductRequest;
import com.saadcodes.techsphere.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product updateProduct(ProductUpdateRequest product, Long productId);
    Product getProductById(Long productId);
    void deleteProductById(Long productId);

    List<Product> getAllProducts();
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByBrandAndName(String brand, String name);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrand(String brand);

    List<ProductDto> getConvertedProducts(List<Product> products);

    ProductDto convertToDTO(Product product);

}
