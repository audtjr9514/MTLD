package com.mtld.backend.dto.diary.walking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * created by seongmin on 2022/09/19
 * updated by seongmin on 2022/09/20
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WalkingDetailRequestDto {
    @NotNull(message = "강아지 아이디는 필수 입력값 입니다.")
    private Long dogId;
    @NotBlank(message = "날짜는 필수 입력값 입니다.")
    private String diaryDate;
}
