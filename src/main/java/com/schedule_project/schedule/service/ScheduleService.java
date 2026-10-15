package com.schedule_project.schedule.service;

import com.schedule_project.schedule.dto.ScheduleCreateRequest;
import com.schedule_project.schedule.dto.ScheduleResponse;
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
    public ScheduleResponse scheduleCreate(ScheduleCreateRequest request){
        Schedule schedule = new Schedule(request.getTitle(),request.getContent(),request.getAuthor(), request.getPassword() );
        Schedule saveSchedule = scheduleRepository.save(schedule);
        ScheduleResponse response = new ScheduleResponse(
                saveSchedule.getScheduleId(),
                saveSchedule.getTitle(),
                saveSchedule.getContent(),
                saveSchedule.getAuthor(),
                saveSchedule.getCreatedAt(),
                saveSchedule.getModifiedAt()
        );
        return response;
    }

    @Transactional(readOnly = true)
    public ScheduleResponse scheduleGetOne(Long id){
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("존재하지 않는 일정입니다."));
        ScheduleResponse response = new ScheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
        return response;
    }
}
