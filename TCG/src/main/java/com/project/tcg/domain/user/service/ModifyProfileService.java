package com.project.tcg.domain.user.service;

import com.project.tcg.domain.image.controller.dto.response.ImageUrlResponse;
import com.project.tcg.domain.image.service.ImageUploadService;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ModifyProfileService {

    private final ImageUploadService imageUploadService;

    private final UserFacade userFacade;

    @Transactional
    public void execute(MultipartFile file) {

        ImageUrlResponse response = imageUploadService.execute(List.of(file));
        String profileUrl = response.getImageUrl().get(0);

        User user = userFacade.getCurrentUser();

        user.setProfileImageUrl(profileUrl);
    }
}