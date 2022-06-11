package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.SportEvent;
import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SportEventRepository extends JpaRepository<SportEvent, Long> {

    Optional<SportEvent> findByName(String name);

    Optional<SportEvent> findBytimeTableInfo(TimeTableInfo timeTableInfo);

    List<SportEvent> findAllByStudent(User student);

    List<SportEvent> findAllByStudentAndTimeTableInfo(User student, TimeTableInfo timeTableInfo);
}
