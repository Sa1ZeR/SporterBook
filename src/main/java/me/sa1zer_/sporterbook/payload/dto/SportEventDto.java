package me.sa1zer_.sporterbook.payload.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SportEventDto {

    private Long id;
    private String name;
    private UserDto student;
    private int result;
    private int maxResult;
    private UserDto trainer;
    private TimeTableInfoDto timeTableInfo;
}
