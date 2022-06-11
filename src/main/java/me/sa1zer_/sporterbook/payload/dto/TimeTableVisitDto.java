package me.sa1zer_.sporterbook.payload.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeTableVisitDto {

    private boolean visit;
    private UserDto student;
    private TimeTableInfoDto date;
}
