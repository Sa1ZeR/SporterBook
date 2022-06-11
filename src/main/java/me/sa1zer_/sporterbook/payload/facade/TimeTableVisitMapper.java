package me.sa1zer_.sporterbook.payload.facade;

import me.sa1zer_.sporterbook.domain.model.TimeTabletVisit;
import me.sa1zer_.sporterbook.payload.dto.TimeTableVisitDto;
import me.sa1zer_.sporterbook.payload.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class TimeTableVisitMapper implements Mapper<TimeTabletVisit, TimeTableVisitDto>{

    private final UserMapper userMapper;
    private final TimeTableInfoMapper timeTableInfoMapper;

    public TimeTableVisitMapper(UserMapper userMapper, TimeTableInfoMapper timeTableInfoMapper) {
        this.userMapper = userMapper;
        this.timeTableInfoMapper = timeTableInfoMapper;
    }

    @Override
    public TimeTableVisitDto map(TimeTabletVisit from) {
        return TimeTableVisitDto.builder()
                .student((UserDto) userMapper.map(from.getStudent()))
                .date(timeTableInfoMapper.map(from.getDate()))
                .visit(from.isVisit())
                .build();
    }
}
