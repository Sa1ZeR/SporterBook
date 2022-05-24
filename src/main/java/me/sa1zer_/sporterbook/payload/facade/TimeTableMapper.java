package me.sa1zer_.sporterbook.payload.facade;

import lombok.Data;
import me.sa1zer_.sporterbook.domain.model.TimeTable;
import me.sa1zer_.sporterbook.payload.dto.TimeTableDto;
import org.springframework.stereotype.Component;

@Component
public class TimeTableMapper implements Mapper<TimeTable, TimeTableDto>{
    @Override
    public TimeTableDto map(TimeTable from) {
        TimeTableDto timeTableDto = new TimeTableDto();

        timeTableDto.setId(from.getId());
        timeTableDto.setStart(from.getDateStart());
        timeTableDto.setEnd(from.getDateEnd());

        return timeTableDto;
    }
}
