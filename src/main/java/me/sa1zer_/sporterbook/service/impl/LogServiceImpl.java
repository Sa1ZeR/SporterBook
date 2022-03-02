package me.sa1zer_.sporterbook.service.impl;

import me.sa1zer_.sporterbook.exception.LogNotFoundException;
import me.sa1zer_.sporterbook.domain.model.Log;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.domain.model.enums.LogType;
import me.sa1zer_.sporterbook.domain.model.enums.Role;
import me.sa1zer_.sporterbook.repository.LogRepository;
import me.sa1zer_.sporterbook.service.LogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public Log findById(Long id) {
        return logRepository.findById(id).orElseThrow(() ->
                new LogNotFoundException(String.format("Лог с id %s не найден!", id)));
    }

    @Override
    public List<Log> findByUser(User user) {
        return logRepository.findByUserId(user);
    }

    @Override
    public List<Log> findByLogType(LogType type) {
        return logRepository.findByType(type);
    }

    @Override
    public Log newLog(String message, User user, LogType logType) {
        Log log = new Log();

        log.setMessage(message);
        log.setType(logType);
        log.setUserId(user);

        return save(log);
    }

    @Override
    public Log save(Log log) {
        return logRepository.save(log);
    }

    @Override
    public void delete(Log log) {
        logRepository.delete(log);
    }
}
