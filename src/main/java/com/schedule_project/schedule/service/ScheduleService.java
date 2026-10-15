package com.schedule_project.schedule.service;

import com.schedule_project.schedule.dto.ScheduleCreateRequest;
import com.schedule_project.schedule.dto.ScheduleCreateResponse;
import com.schedule_project.schedule.entity.Schedule;
import com.schedule_project.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleCreateResponse scheduleCreate(ScheduleCreateRequest request){
        Schedule schedule = new Schedule(request.getTitle(),request.getContent(),request.getAuthor(), request.getPassword() );
        Schedule saveSchedule = scheduleRepository.save(schedule);
        ScheduleCreateResponse response = new ScheduleCreateResponse(
                saveSchedule.getScheduleId(),
                saveSchedule.getTitle(),
                saveSchedule.getContent(),
                saveSchedule.getAuthor(),
                saveSchedule.getCreatedAt(),
                saveSchedule.getModifiedAt()
        );
        return response;
    }
}
