package me.sa1zer_.sporterbook.service.impl;

import me.sa1zer_.sporterbook.domain.model.TimeTable;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.exception.TimeTableNotFound;
import me.sa1zer_.sporterbook.repository.TimeTableRepository;
import me.sa1zer_.sporterbook.service.TimeTableService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeTableServiceImpl implements TimeTableService {

    private final TimeTableRepository timeTableRepository;

    public TimeTableServiceImpl(TimeTableRepository timeTableRepository) {
        this.timeTableRepository = timeTableRepository;
    }

    @Override
    public List<TimeTable> findAll() {
        return timeTableRepository.findAll();
    }

    @Override
    public List<TimeTable> findAll(Pageable pageable) {
        return new ArrayList<>();
    }

    @Override
    public TimeTable findById(Long id) {
        return timeTableRepository.findById(id).orElseThrow(()
                -> new TimeTableNotFound(String.format("TimeTable with id %s not found", id)));
    }

    @Override
    public List<TimeTable> findByDateStart(LocalDateTime time) {
        return timeTableRepository.findByDateStart(time);
    }

    @Override
    public List<TimeTable> findByDateEnd(LocalDateTime time) {
        return timeTableRepository.findByDateStart(time);
    }

    @Override
    public List<TimeTable> findByDateStartAndDateEnd(LocalDateTime start, LocalDateTime end) {
        return timeTableRepository.findByDateStartAndDateEnd(start, end);
    }

    @Override
    public List<TimeTable> findByDateStartAndDateEnd(LocalDateTime start, LocalDateTime end,
                                                     Pageable pageable) {
        return timeTableRepository.findByDateStartAndDateEnd(start, end, pageable);
    }

    @Override
    public TimeTable create(LocalDateTime start, LocalDateTime end) {
        TimeTable timeTable = new TimeTable();

        timeTable.setDateStart(start);
        timeTable.setDateEnd(end);

        //todo timetime info

        return save(timeTable);
    }

    @Override
    public TimeTable save(TimeTable timeTable) {
        return timeTableRepository.save(timeTable);
    }

    @Override
    public void delete(TimeTable timeTable) {
        timeTableRepository.delete(timeTable);
    }
}
