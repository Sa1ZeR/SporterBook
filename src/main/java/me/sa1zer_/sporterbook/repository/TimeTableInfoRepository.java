package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface TimeTableInfoRepository extends JpaRepository<TimeTableInfo, Long> {

    List<TimeTableInfo> findBySection(SportSection sportSection);

    List<TimeTableInfo> findByCategory(TimeTableCategory category);

    List<TimeTableInfo> findByRoom(Room room);

    List<TimeTableInfo> findByTimeTable(TimeTable timeTable);

    //custom

    @Query("select tti from TimeTableInfo tti where tti.section in :sections " +
            "and tti.timeTable in (select tt from TimeTable tt where tt.dateStart >= :startDate)")
    List<TimeTableInfo> findAllTimeTable(@Param("sections") Set<SportSection> sections,
                                         @Param("startDate") LocalDateTime startDate);
}
