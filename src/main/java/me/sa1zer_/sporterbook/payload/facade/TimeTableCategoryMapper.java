package me.sa1zer_.sporterbook.payload.facade;

import me.sa1zer_.sporterbook.domain.model.TimeTableCategory;
import me.sa1zer_.sporterbook.payload.dto.TimeTableCategoryDto;
import org.springframework.stereotype.Component;

@Component
public class TimeTableCategoryMapper implements Mapper<TimeTableCategory, TimeTableCategoryDto>{
    @Override
    public TimeTableCategoryDto map(TimeTableCategory from) {
        TimeTableCategoryDto timeTableCategoryDto = new TimeTableCategoryDto();

        timeTableCategoryDto.setId(from.getId());
        timeTableCategoryDto.setName(from.getName());

        return timeTableCategoryDto;
    }
}
