package com.schedule_project.schedule.repository;


import com.schedule_project.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // SELECT * FROM WHERE author = ?
    List<Schedule> findAllByAuthor(String author);
}
