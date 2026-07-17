package com.saadcodes.techsphere.service.image;

import com.saadcodes.techsphere.dtos.ImageDto;
import com.saadcodes.techsphere.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long imageId);
    void deleteImageById(Long imageId);
    void updateImage(MultipartFile file, Long imageId);
    List<ImageDto> saveImage(Long productId, List<MultipartFile> files);
}
