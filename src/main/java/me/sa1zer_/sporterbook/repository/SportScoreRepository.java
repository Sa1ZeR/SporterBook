package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.SportScore;
import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SportScoreRepository extends JpaRepository<SportScore, Long> {

    Optional<SportScore> findByName(String name);

    Optional<SportScore> findBytimeTableInfo(TimeTableInfo timeTableInfo);

    List<SportScore> findAllByStudent(User student);

    List<SportScore> findAllByStudentAndTimeTableInfo(User student, TimeTableInfo timeTableInfo);

    List<SportScore> findAllByStudentAndSportSection(User student, SportSection sportSection);
}
