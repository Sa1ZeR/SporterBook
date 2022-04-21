package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.domain.model.TimeTabletVisit;
import me.sa1zer_.sporterbook.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TimeTableVisitRepository extends JpaRepository<TimeTabletVisit, Long> {

    List<TimeTabletVisit> findAllByDate(TimeTableInfo info);

    TimeTabletVisit findAllByStudentAndDate(User student, TimeTableInfo info);

    List<TimeTabletVisit> findAllByStudent(User user);

    List<TimeTabletVisit> findAllByVisit(boolean is);

    List<TimeTabletVisit> findAllByStudentAndVisit(User student, boolean is);
}
