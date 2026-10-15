package com.schedule_project.schedule.controller;

import com.schedule_project.schedule.dto.ScheduleCreateRequest;
import com.schedule_project.schedule.dto.ScheduleResponse;
import com.schedule_project.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 일정 생성 API
     * @param request 일정 생성 요청 DTO (제목, 내용, 작성자명, 비밀번호)
     * @return 생성된 일정 정보
     */
    @PostMapping
    public ScheduleResponse scheduleCreate(@Valid @RequestBody ScheduleCreateRequest request){
        ScheduleResponse respone = scheduleService.scheduleCreate(request);
        return respone;
    }

    /**
     * 선택 일정 조회 API
     * @param scheduleId 조회할 일정 ID
     * @return 조회된 일정 정보
     */
    @GetMapping("/{scheduleId}")
    public ScheduleResponse scheduleGetOne(@PathVariable Long scheduleId){
        ScheduleResponse response = scheduleService.scheduleGetOne(scheduleId);
        return response;
    }


    /**
     * 전체 일정 조회 API
     * @param author 작성자명 (선택, 입력 시 해당 작성자의 일정만 조회)
     * @return 일정 목록
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getSchedules(@RequestParam(required = false) String author){
        List<ScheduleResponse> response = scheduleService.getSchedules(author);
        return ResponseEntity.ok(response);
    }
}
