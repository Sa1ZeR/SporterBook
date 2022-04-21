package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.domain.model.TimeTabletVisit;
import me.sa1zer_.sporterbook.domain.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TimeTableVisitService {

    TimeTabletVisit findById(Long id);

    List<TimeTabletVisit> findAll();

    List<TimeTabletVisit> findAll(Pageable pageable);

    List<TimeTabletVisit> findAllByDate(TimeTableInfo info);

    TimeTabletVisit findByUserAndDate(User student, TimeTableInfo info);

    List<TimeTabletVisit> findAllByStudent(User user);

    List<TimeTabletVisit> findByVisit(boolean is);

    List<TimeTabletVisit> findByStudentAndVisit(User student, boolean is);

    TimeTabletVisit save(TimeTabletVisit visit);

    void delete(TimeTabletVisit visit);
}
