package com.project.tcg.domain.card.presentation.dto.request;

import com.project.tcg.domain.card.domain.enums.Grade;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCardRequest {

    @NotBlank(message = "이름은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    private String name;

    @NotBlank(message = "cardImageUrl은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    private String cardImageUrl;

    @NotNull
    private Grade grade;

    @NotNull
    private String description;
}