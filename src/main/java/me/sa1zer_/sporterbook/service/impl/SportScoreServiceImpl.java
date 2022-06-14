package me.sa1zer_.sporterbook.service.impl;

import me.sa1zer_.sporterbook.domain.model.SportScore;
import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.exception.SportEventNotFound;
import me.sa1zer_.sporterbook.repository.SportScoreRepository;
import me.sa1zer_.sporterbook.service.SportScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportScoreServiceImpl implements SportScoreService {

    private final SportScoreRepository sportScoreRepository;

    public SportScoreServiceImpl(SportScoreRepository sportScoreRepository) {
        this.sportScoreRepository = sportScoreRepository;
    }

    @Override
    public SportScore findById(Long id) {
        return sportScoreRepository.findById(id).orElseThrow(() ->
                new SportEventNotFound(String.format("SportEvent with id %s not found", id)));
    }

    @Override
    public SportScore findByName(String name) {
        return sportScoreRepository.findByName(name).orElseThrow(() ->
                new SportEventNotFound(String.format(
                        "SportEvent with name %s not found", name)));
    }

    @Override
    public SportScore findBytimeTableInfo(TimeTableInfo timeTableInfo) {
        return sportScoreRepository.findBytimeTableInfo(timeTableInfo).orElseThrow(() ->
                new SportEventNotFound(String.format(
                        "SportEvent with info id %s not found", timeTableInfo.getId())));
    }

    @Override
    public List<SportScore> findAllByStudent(User user) {
        return sportScoreRepository.findAllByStudent(user);
    }

    @Override
    public List<SportScore> findAllByStudentAndTimeTableInfo(User user, TimeTableInfo timeTableInfo) {
        return sportScoreRepository.findAllByStudentAndTimeTableInfo(user, timeTableInfo);
    }

    @Override
    public List<SportScore> findAllByStudentAndSportSection(User user, SportSection sportSection) {
        return sportScoreRepository.findAllByStudentAndSportSection(user, sportSection);
    }

    @Override
    public SportScore save(SportScore sportScore) {
        return sportScoreRepository.save(sportScore);
    }

    @Override
    public void delete(SportScore sportScore) {
        sportScoreRepository.delete(sportScore);
    }
}
