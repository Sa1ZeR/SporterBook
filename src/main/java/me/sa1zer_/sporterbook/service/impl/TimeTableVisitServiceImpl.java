package me.sa1zer_.sporterbook.service.impl;

import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.domain.model.TimeTabletVisit;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.exception.TimeTableVisitNotFoundException;
import me.sa1zer_.sporterbook.repository.TimeTableVisitRepository;
import me.sa1zer_.sporterbook.service.TimeTableVisitService;
import org.springframework.data.domain.Pageable;

import java.util.List;


public class TimeTableVisitServiceImpl implements TimeTableVisitService {

    private final TimeTableVisitRepository visitRepository;

    public TimeTableVisitServiceImpl(TimeTableVisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public TimeTabletVisit findById(Long id) {
        return visitRepository.findById(id).orElseThrow(() ->
                new TimeTableVisitNotFoundException(
                        String.format("TimeTableVisit with id %s not found!!", id)));
    }

    @Override
    public List<TimeTabletVisit> findAll() {
        return visitRepository.findAll();
    }

    @Override
    public List<TimeTabletVisit> findAll(Pageable pageable) {
        return visitRepository.findAll(pageable).toList();
    }

    @Override
    public TimeTabletVisit findByUserAndDate(User student, TimeTableInfo info) {
        return visitRepository.findAllByStudentAndDate(student, info);
    }

    @Override
    public List<TimeTabletVisit> findAllByDate(TimeTableInfo info) {
        return visitRepository.findAllByDate(info);
    }

    @Override
    public List<TimeTabletVisit> findAllByStudent(User user) {
        return visitRepository.findAllByStudent(user);
    }

    @Override
    public List<TimeTabletVisit> findByVisit(boolean is) {
        return visitRepository.findAllByVisit(is);
    }

    @Override
    public List<TimeTabletVisit> findByStudentAndVisit(User student, boolean is) {
        return visitRepository.findAllByStudentAndVisit(student, is);
    }


    @Override
    public TimeTabletVisit save(TimeTabletVisit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public void delete(TimeTabletVisit visit) {
        visitRepository.delete(visit);
    }
}
