package me.sa1zer_.sporterbook.payload.facade;

import me.sa1zer_.sporterbook.domain.model.SportEvent;
import me.sa1zer_.sporterbook.payload.dto.SportEventDto;
import me.sa1zer_.sporterbook.payload.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class SportEventMapper implements Mapper<SportEvent, SportEventDto> {

    private final UserMapper userMapper;
    private final TimeTableInfoMapper timeTableInfoMapper;

    public SportEventMapper(UserMapper userMapper, TimeTableInfoMapper timeTableInfoMapper) {
        this.userMapper = userMapper;
        this.timeTableInfoMapper = timeTableInfoMapper;
    }

    @Override
    public SportEventDto map(SportEvent from) {
        return SportEventDto.builder()
                .id(from.getId())
                .name(from.getName())
                .student((UserDto) userMapper.map(from.getStudent()))
                .result(from.getResult())
                .maxResult(from.getMaxResult())
                .trainer((UserDto) userMapper.map(from.getTrainer()))
                .timeTableInfo(timeTableInfoMapper.map(from.getTimeTableInfo()))
                .build();
    }
}
