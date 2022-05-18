package me.sa1zer_.sporterbook.service.impl;

import me.sa1zer_.sporterbook.exception.LogNotFoundException;
import me.sa1zer_.sporterbook.domain.model.Log;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.domain.model.enums.LogType;
import me.sa1zer_.sporterbook.domain.model.enums.Role;
import me.sa1zer_.sporterbook.repository.LogRepository;
import me.sa1zer_.sporterbook.service.LogService;
import org.springframework.data.domain.Pageable;
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
    public List<Log> findALl() {
        return logRepository.findAll();
    }

    @Override
    public List<Log> findALl(Pageable pageable) {
        return logRepository.findAll(pageable).toList();
    }

    @Override
    public List<Log> findALlByType(LogType logType, Pageable pageable) {
        return logRepository.findAllByType(logType, pageable);
    }

    @Override
    public List<Log> findAllByUser(User user) {
        return logRepository.findAllByUserId(user);
    }

    @Override
    public List<Log> findAllByUser(User user, Pageable pageable) {
        return logRepository.findAllByUserId(user, pageable);
    }

    @Override
    public List<Log> findAllByUserAndLogType(User user, LogType logType) {
        return logRepository.findAllByUserIdAndType(user, logType);
    }

    @Override
    public List<Log> findAllByUserAndLogType(User user, LogType logType, Pageable pageable) {
        return logRepository.findAllByUserIdAndType(user, logType, pageable);
    }

    @Override
    public List<Log> findAllByLogType(LogType type) {
        return logRepository.findAllByType(type);
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
