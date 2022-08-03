package com.project.tcg.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UpdateUserInfoRequest {

    @NotBlank(message = "username은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    private String username;

    private String profileImageUrl;
}