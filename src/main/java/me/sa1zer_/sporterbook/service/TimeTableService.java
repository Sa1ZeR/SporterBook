package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.TimeTable;
import me.sa1zer_.sporterbook.domain.model.User;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface TimeTableService {

    List<TimeTable> findAll();

    List<TimeTable> findAll(Pageable pageable);

    TimeTable findById(Long id);

    List<TimeTable> findByDateStart(LocalDateTime time);

    List<TimeTable> findByDateEnd(LocalDateTime time);

    List<TimeTable> findByDateStartAndDateEnd(LocalDateTime start, LocalDateTime end);

    List<TimeTable> findByDateStartAndDateEnd(LocalDateTime start, LocalDateTime end,
                                              Pageable pageable);

    TimeTable create(LocalDateTime start, LocalDateTime end);

    TimeTable save(TimeTable timeTable);

    void delete(TimeTable timeTable);

}
