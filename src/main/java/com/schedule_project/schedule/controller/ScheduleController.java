package com.schedule_project.schedule.controller;

import com.schedule_project.schedule.dto.ScheduleCreateRequest;
import com.schedule_project.schedule.dto.ScheduleResponse;
import com.schedule_project.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleResponse scheduleCreate(@Valid @RequestBody ScheduleCreateRequest request){
        ScheduleResponse respone = scheduleService.scheduleCreate(request);
        return respone;
    }

    @GetMapping("/{id}")
    public ScheduleResponse scheduleGetOne(@PathVariable Long author){
        ScheduleResponse response = scheduleService.scheduleGetOne(author);
        return response;
    }
}
