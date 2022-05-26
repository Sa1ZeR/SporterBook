package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface TimeTableInfoService {

    TimeTableInfo findById(Long id);

    List<TimeTableInfo> findBySection(SportSection sportSection);

    List<TimeTableInfo> findByCategory(TimeTableCategory category);

    List<TimeTableInfo> findByRoom(Room room);

    List<TimeTableInfo> findByTimeTable(TimeTable timeTable);

    List<TimeTableInfo> findAllTimeTable(Set<SportSection> sections, LocalDateTime localDateTime);

    List<TimeTableInfo> findAll();

    TimeTableInfo create(TimeTable timeTable, SportSection section, Room room, TimeTableCategory category);

    TimeTableInfo save(TimeTableInfo timeTableInfo);

    void delete(TimeTableInfo timeTableInfo);
}
