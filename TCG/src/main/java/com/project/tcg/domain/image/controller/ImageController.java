package com.project.tcg.domain.image.controller;

import com.project.tcg.domain.image.controller.dto.response.ImageUrlResponse;
import com.project.tcg.domain.image.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ImageController {

    private final ImageUploadService imageUploadService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/image")
    public ImageUrlResponse saveImage(@RequestPart List<MultipartFile> images) {
        return imageUploadService.execute(images);
    }
}