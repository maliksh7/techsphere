package com.saadcodes.techsphere.controller;

import com.saadcodes.techsphere.dtos.ImageDto;
import com.saadcodes.techsphere.model.Image;
import com.saadcodes.techsphere.response.ApiResponse;
import com.saadcodes.techsphere.service.image.IImageService;
import com.saadcodes.techsphere.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/images")
public class ImageController {

    private final IImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadImages(@RequestParam("files") List<MultipartFile> files, @RequestParam("productId") Long productId) {
        List<ImageDto> imageDto = imageService.saveImage(productId, files);
        return ResponseEntity.ok(new ApiResponse("Images uploaded successfully", imageDto));
    }

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        +image.getFileName() + "\"").body(resource);
    }


    /*
    * Implement the delete and update endpoints for images
    * Remember to handle exceptions properly and return appropriate responses
    * */

    @PutMapping("/update/{imageId}")
    public ResponseEntity<ApiResponse> updateImage(@RequestParam("file") MultipartFile file,@PathVariable Long imageId) {
        imageService.updateImage(file, imageId);
        return ResponseEntity.ok(new ApiResponse("Image updated successfully", null));
    }

    @DeleteMapping("/delete/{imageId}")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImageById(imageId);
        return ResponseEntity.ok(new ApiResponse("Image deleted successfully", null));
    }
}

