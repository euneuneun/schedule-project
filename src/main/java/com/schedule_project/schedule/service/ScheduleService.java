package com.schedule_project.schedule.service;

import com.schedule_project.schedule.dto.request.ScheduleCreateRequest;
import com.schedule_project.schedule.dto.request.ScheduleUpdateRequest;
import com.schedule_project.schedule.dto.response.ScheduleResponse;
import com.schedule_project.schedule.entity.Schedule;
import com.schedule_project.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional(readOnly = true)
    public List<ScheduleResponse> getSchedules(String author){

        List<Schedule> schedules = scheduleRepository.findAllByAuthor(author);

        if(author == null){
            schedules = scheduleRepository.findAll();
        }

        List<ScheduleResponse> response = new ArrayList<>();

        for (Schedule schedule : schedules){
            response.add(new ScheduleResponse(
                    schedule.getScheduleId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getAuthor(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()));
        }

        return response;
    }

    @Transactional
    public ScheduleResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest request){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow( () -> new IllegalStateException("일정이 존재하지 않습니다."));
        if ( schedule.getPassword().equals(request.getPassword())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        schedule.update(request.getTitle(), request.getAuthor());

        ScheduleResponse response = new ScheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());

        return response;
    }

    @Transactional
    public void deleteSchedule(Long scheduleId, String password){

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(()->new IllegalStateException("일정이 존재하지 않습니다"));
        if ( schedule.getPassword().equals(password) ){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.delete(schedule);
    }
}
