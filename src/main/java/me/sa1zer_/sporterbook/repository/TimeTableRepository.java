package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.TimeTable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

    List<TimeTable> findByDateStart(LocalDateTime start);

    List<TimeTable> findByDateEnd(LocalDateTime end);

    List<TimeTable> findByDateStartAndDateEnd(LocalDateTime start, LocalDateTime end);

    List<TimeTable> findByDateStartAndDateEnd(LocalDateTime start, LocalDateTime end,
                                              Pageable pageable);


}
