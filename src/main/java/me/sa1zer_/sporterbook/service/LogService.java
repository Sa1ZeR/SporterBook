package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.Log;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.domain.model.enums.LogType;
import me.sa1zer_.sporterbook.domain.model.enums.Role;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface LogService {

    Log findById(Long id);

    List<Log> findALl();

    List<Log> findALl(Pageable pageable);

    List<Log> findALlByType(LogType logType, Pageable pageable);

    List<Log> findAllByUser(User user);

    List<Log> findAllByUser(User user, Pageable pageable);

    List<Log> findAllByUserAndLogType(User user, LogType logType);

    List<Log> findAllByUserAndLogType(User user, LogType logType, Pageable pageable);

    List<Log> findAllByLogType(LogType type);

    Log newLog(String message, User user, LogType logType);

    Log save(Log log);

    void delete(Log log);
}
