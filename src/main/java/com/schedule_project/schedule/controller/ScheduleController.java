package com.schedule_project.schedule.controller;

import com.schedule_project.schedule.dto.ScheduleCreateRequest;
import com.schedule_project.schedule.dto.ScheduleCreateResponse;
import com.schedule_project.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ScheduleCreateResponse scheduleCreate(@Valid @RequestBody ScheduleCreateRequest request){
        ScheduleCreateResponse respone = scheduleService.scheduleCreate(request);
        return respone;
    }
}
