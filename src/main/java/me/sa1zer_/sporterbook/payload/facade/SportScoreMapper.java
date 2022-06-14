package me.sa1zer_.sporterbook.payload.facade;

import me.sa1zer_.sporterbook.domain.model.SportScore;
import me.sa1zer_.sporterbook.payload.dto.SportEventDto;
import me.sa1zer_.sporterbook.payload.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class SportScoreMapper implements Mapper<SportScore, SportEventDto> {

    private final UserMapper userMapper;
    private final TimeTableInfoMapper timeTableInfoMapper;
    private final SportSectionMapper sportSectionMapper;

    public SportScoreMapper(UserMapper userMapper, TimeTableInfoMapper timeTableInfoMapper,
                            SportSectionMapper sportSectionMapper) {
        this.userMapper = userMapper;
        this.timeTableInfoMapper = timeTableInfoMapper;
        this.sportSectionMapper = sportSectionMapper;
    }

    @Override
    public SportEventDto map(SportScore from) {
        return SportEventDto.builder()
                .id(from.getId())
                .name(from.getName())
                .student((UserDto) userMapper.map(from.getStudent()))
                .result(from.getResult())
                .maxResult(from.getMaxResult())
                .trainer((UserDto) userMapper.map(from.getTrainer()))
                .timeTableInfo(timeTableInfoMapper.map(from.getTimeTableInfo()))
                .sportSectionDto(sportSectionMapper.map(from.getSportSection()))
                .build();
    }
}
