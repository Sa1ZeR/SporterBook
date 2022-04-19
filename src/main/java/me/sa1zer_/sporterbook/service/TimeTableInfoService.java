package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.*;

import java.util.List;

public interface TimeTableInfoService {

    TimeTableInfo findById(Long id);

    List<TimeTableInfo> findBySection(SportSection sportSection);

    List<TimeTableInfo> findByCategory(TimeTableCategory category);

    List<TimeTableInfo> findByRoom(Room room);

    List<TimeTableInfo> findByTimeTable(TimeTable timeTable);

    List<TimeTableInfo> findAll();

    TimeTableInfo save(TimeTableInfo timeTableInfo);

    void delete(TimeTableInfo timeTableInfo);
}
