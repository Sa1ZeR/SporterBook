package me.sa1zer_.sporterbook.payload.facade;

import me.sa1zer_.sporterbook.domain.model.Log;
import me.sa1zer_.sporterbook.payload.dto.LogDto;
import org.springframework.stereotype.Component;

@Component
public class LogMapper implements Mapper<Log, LogDto> {

    @Override
    public LogDto map(Log from) {
        LogDto logDto = new LogDto();

        logDto.setUser(from.getUserId().getLogin());
        logDto.setMessage(from.getMessage());
        logDto.setLogType(from.getType());
        logDto.setDate(from.getCreated());

        return logDto;
    }
}
