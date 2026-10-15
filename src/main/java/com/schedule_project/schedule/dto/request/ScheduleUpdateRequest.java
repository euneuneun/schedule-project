package com.schedule_project.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleUpdateRequest {

    @Size(max=30, message = "30자 이내로 입력해주세요.")
    private String title;

    @Size(max=50, message = "50자 이내로 입력해주세요.")
    private String author;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(max=4, message = "4자리 이내로 입력해주세요.")
    private String password;
}
