package com.mtld.backend.controller;

import com.mtld.backend.dto.diary.DiaryResponseDto;
import com.mtld.backend.dto.diary.record.RecordDetailResponseDto;
import com.mtld.backend.dto.diary.record.RecordRequestDto;
import com.mtld.backend.dto.diary.walking.WalkingDetailRequestDto;
import com.mtld.backend.dto.diary.walking.WalkingDetailResponseDto;
import com.mtld.backend.dto.diary.walking.WalkingRequestDto;
import com.mtld.backend.service.diary.DiaryService;
import com.mtld.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * created by seongmin on 2022/09/19
 * updated by seongmin on 2022/09/21
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/diary")
public class DiaryController {
    private final UserService userService;
    private final DiaryService diaryService;

    @PostMapping("/record")
    public ResponseEntity<?> writeRecord(@RequestPart(value = "record") @Valid RecordRequestDto requestDto,
                                         @RequestPart(value = "image", required = false) List<MultipartFile> multipartFiles) {

        Long diaryId = diaryService.writeRecord(userService.getMyInfoSecret().getId(), requestDto, multipartFiles);
        return ResponseEntity.status(CREATED).body(diaryId);
    }

    @GetMapping("/record/{id}")
    public ResponseEntity<?> recordDetailById(@PathVariable(value = "id") Long id) {
        RecordDetailResponseDto result = diaryService.getRecordDetailById(userService.getMyInfoSecret().getId(), id);
        return ResponseEntity.status(OK).body(result);
    }

    @GetMapping("/record/date/{date}")
    public ResponseEntity<?> recordDetailByDate(@PathVariable(value = "date") @NotBlank String date) {
        RecordDetailResponseDto result = diaryService.getRecordDetailByDate(userService.getMyInfoSecret().getId(), date);
        return ResponseEntity.status(OK).body(result);
    }

    @DeleteMapping("/record/{id}")
    public ResponseEntity<?> deleteRecord(@PathVariable(value = "id") Long id) {
        diaryService.deleteRecord(userService.getMyInfoSecret().getId(), id);
        return ResponseEntity.status(OK).build();
    }

    @PostMapping("/walking")
    public ResponseEntity<?> writeWalking(@RequestBody @Valid WalkingRequestDto requestDto) {
        Long diaryId = diaryService.writeWalking(userService.getMyInfoSecret().getId(), requestDto);
        return ResponseEntity.status(OK).body(diaryId);
    }

    @GetMapping("/walking/{id}")
    public ResponseEntity<?> walkingDetailById(@PathVariable(value = "id") Long id) {
        WalkingDetailResponseDto result = diaryService.getWalkingDetailById(userService.getMyInfoSecret().getId(), id);
        return ResponseEntity.status(OK).body(result);
    }

    @GetMapping("/walking/date")
    public ResponseEntity<?> walkingDetail(@RequestParam("dogId") Long dogId, @RequestParam("date") String date) {
        log.info("dogId = {}", dogId);
        log.info("date = {}", date);
        WalkingDetailResponseDto result = diaryService.getWalkingDetail(userService.getMyInfoSecret().getId(), dogId, date);
        return ResponseEntity.status(OK).body(result);
    }

    @DeleteMapping("/walking/{id}")
    public ResponseEntity<?> deleteWalking(@PathVariable(value = "id") Long id) {
        diaryService.deleteWalking(userService.getMyInfoSecret().getId(), id);
        return ResponseEntity.status(OK).build();
    }

    @GetMapping
    public ResponseEntity<?> allDiaryDate() {
        DiaryResponseDto result = diaryService.getMyDiaryDate(userService.getMyInfoSecret().getId());
        return ResponseEntity.status(OK).body(result);
    }

}
