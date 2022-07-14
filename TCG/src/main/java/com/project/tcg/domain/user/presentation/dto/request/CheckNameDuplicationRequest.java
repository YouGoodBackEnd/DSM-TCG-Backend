package com.project.tcg.domain.user.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckNameDuplicationRequest {

    @NotBlank(message = "이름은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    private String name;
}