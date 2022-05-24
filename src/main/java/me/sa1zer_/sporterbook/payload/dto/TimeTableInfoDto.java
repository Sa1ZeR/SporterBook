package me.sa1zer_.sporterbook.payload.dto;

import lombok.Data;

@Data
public class TimeTableInfoDto {

    private Long id;
    private TimeTableDto timeTableDto;
    private TimeTableCategoryDto categoryDto;
    private SportSectionDto sectionDto;
    private RoomDto roomDto;
}
