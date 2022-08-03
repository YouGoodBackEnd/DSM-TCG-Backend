package com.project.tcg.infrastructure.image;


import org.springframework.web.multipart.MultipartFile;

public interface ImageUtil {
    String uploadImage(MultipartFile image);
}