package me.sa1zer_.sporterbook.service.impl;

import me.sa1zer_.sporterbook.domain.model.*;
import me.sa1zer_.sporterbook.exception.TimeTableInfoNotFoundException;
import me.sa1zer_.sporterbook.repository.TimeTableInfoRepository;
import me.sa1zer_.sporterbook.service.TimeTableInfoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class TImeTableInfoServiceImpl implements TimeTableInfoService {

    private final TimeTableInfoRepository timeTableInfoRepository;

    public TImeTableInfoServiceImpl(TimeTableInfoRepository timeTableInfoRepository) {
        this.timeTableInfoRepository = timeTableInfoRepository;
    }

    @Override
    public TimeTableInfo findById(Long id) {
        return timeTableInfoRepository.findById(id).orElseThrow(() ->
                new TimeTableInfoNotFoundException(
                        String.format("TimeTableInfo with id %s not found", id)));
    }

    @Override
    public List<TimeTableInfo> findBySection(SportSection sportSection) {
        return timeTableInfoRepository.findBySection(sportSection);
    }

    @Override
    public List<TimeTableInfo> findByCategory(TimeTableCategory category) {
        return timeTableInfoRepository.findByCategory(category);
    }

    @Override
    public List<TimeTableInfo> findByRoom(Room room) {
        return timeTableInfoRepository.findByRoom(room);
    }

    @Override
    public List<TimeTableInfo> findByTimeTable(TimeTable timeTable) {
        return timeTableInfoRepository.findByTimeTable(timeTable);
    }

    @Override
    public List<TimeTableInfo> findAllTimeTable(Set<SportSection> sections, LocalDateTime startDate) {
        return timeTableInfoRepository.findAllTimeTable(sections, startDate);
    }

    @Override
    public List<TimeTableInfo> findAll() {
        return timeTableInfoRepository.findAll();
    }

    @Override
    public TimeTableInfo create(TimeTable timeTable, SportSection section, Room room, TimeTableCategory category) {
        TimeTableInfo timeTableInfo = new TimeTableInfo();

        timeTableInfo.setTimeTable(timeTable);
        timeTableInfo.setSection(section);
        timeTableInfo.setCategory(category);
        timeTableInfo.setRoom(room);

        return save(timeTableInfo);
    }

    @Override
    public TimeTableInfo save(TimeTableInfo timeTableInfo) {
        return timeTableInfoRepository.save(timeTableInfo);
    }

    @Override
    public void delete(TimeTableInfo timeTableInfo) {
        timeTableInfoRepository.delete(timeTableInfo);
    }
}
