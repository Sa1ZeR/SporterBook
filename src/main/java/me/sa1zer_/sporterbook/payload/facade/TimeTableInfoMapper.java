package me.sa1zer_.sporterbook.payload.facade;

import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.payload.dto.TimeTableInfoDto;
import org.springframework.stereotype.Component;

@Component
public class TimeTableInfoMapper implements Mapper<TimeTableInfo, TimeTableInfoDto>{

    private final SportSectionMapper sectionMapper;
    private final TimeTableMapper timeTableMapper;
    private final TimeTableCategoryMapper categoryMapper;
    private final RoomMapper roomMapper;

    public TimeTableInfoMapper(SportSectionMapper sectionMapper, TimeTableMapper timeTableMapper,
                               TimeTableCategoryMapper categoryMapper, RoomMapper roomMapper) {
        this.sectionMapper = sectionMapper;
        this.timeTableMapper = timeTableMapper;
        this.categoryMapper = categoryMapper;
        this.roomMapper = roomMapper;
    }

    @Override
    public TimeTableInfoDto map(TimeTableInfo from) {
        TimeTableInfoDto timeTableInfoDto = new TimeTableInfoDto();

        timeTableInfoDto.setId(from.getId());

        timeTableInfoDto.setTimeTableDto(timeTableMapper.map(from.getTimeTable()));
        timeTableInfoDto.setCategoryDto(categoryMapper.map(from.getCategory()));
        timeTableInfoDto.setSectionDto(sectionMapper.map(from.getSection()));
        timeTableInfoDto.setRoomDto(roomMapper.map(from.getRoom()));

        return timeTableInfoDto;
    }
}
