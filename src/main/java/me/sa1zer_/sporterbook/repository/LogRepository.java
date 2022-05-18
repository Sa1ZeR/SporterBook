package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.Log;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.domain.model.enums.LogType;
import me.sa1zer_.sporterbook.domain.model.enums.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findAllByUserId(User user);

    List<Log> findAllByUserId(User user, Pageable pageable);

    List<Log> findAllByUserIdAndType(User user, LogType logType);

    List<Log> findAllByUserIdAndType(User user, LogType logType, Pageable pageable);

    List<Log> findAllByType(LogType type);

    List<Log> findAllByType(LogType type, Pageable pageable);
}
