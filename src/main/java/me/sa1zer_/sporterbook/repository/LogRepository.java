package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.Log;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.domain.model.enums.LogType;
import me.sa1zer_.sporterbook.domain.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findByUserId(User user);

    List<Log> findByRole(Role role);

    List<Log> findByType(LogType type);
}
