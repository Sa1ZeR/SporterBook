package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableInfoRepository extends JpaRepository<TimeTableInfo, Long> {

    List<TimeTableInfo> findBySection(SportSection sportSection);

    List<TimeTableInfo> findByCategory(TimeTableCategory category);

    List<TimeTableInfo> findByRoom(Room room);

    List<TimeTableInfo> findByTimeTable(TimeTable timeTable);
}
