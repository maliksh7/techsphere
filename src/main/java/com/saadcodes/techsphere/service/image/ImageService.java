package com.saadcodes.techsphere.service.image;

import com.saadcodes.techsphere.dtos.ImageDto;
import com.saadcodes.techsphere.model.Image;
import com.saadcodes.techsphere.model.Product;
import com.saadcodes.techsphere.repository.ImageRepository;
import com.saadcodes.techsphere.repository.ProductRepository;
import com.saadcodes.techsphere.service.product.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final ProductService productService;

    @Override
    public Image getImageById(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Image not found with id: " + imageId));
    }

    @Override
    public void deleteImageById(Long imageId) {
        imageRepository.findById(imageId).ifPresentOrElse(imageRepository::delete,
                () -> {
                    throw new EntityNotFoundException(
                            "Image not found with id: " + imageId);
                });
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<ImageDto> saveImage(Long productId, List<MultipartFile> files) {
        Product product = productService.getProductById(productId);

        List<ImageDto> savedImages = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String builtDownloadUrl = "/api/v1/images/image/download/";
                String downloadUrl = builtDownloadUrl + image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl(builtDownloadUrl + savedImage.getId());
                imageRepository.save(savedImage);

                ImageDto imageDto = new ImageDto();
                imageDto.setId(savedImage.getId());
                imageDto.setFileName(savedImage.getFileName());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());
                savedImages.add(imageDto);
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        return savedImages;
    }


}

