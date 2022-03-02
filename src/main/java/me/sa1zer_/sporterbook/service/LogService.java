package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.Log;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.domain.model.enums.LogType;
import me.sa1zer_.sporterbook.domain.model.enums.Role;

import java.util.List;

public interface LogService {

    Log findById(Long id);

    List<Log> findByUser(User user);

    List<Log> findByRole(Role role);

    List<Log> findByLogType(LogType type);

    Log newLog(String message, User user, LogType logType);

    Log save(Log log);

    void delete(Log log);
}
