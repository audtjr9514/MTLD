package com.mtld.backend.dto.diary.record;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * created by seongmin on 2022/09/08
 * updated by seongmin on 2022/09/20
 */

@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
public class RecordRequestDto {

    @NotBlank(message = "일지 기록 날짜는 필수 입력값입니다.")
    private String diaryDate;

    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String mainText;
}
