package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.SportScore;
import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.domain.model.User;

import java.util.List;

public interface SportScoreService {

    SportScore findById(Long id);

    SportScore findByName(String name);

    SportScore findBytimeTableInfo(TimeTableInfo timeTableInfo);

    List<SportScore> findAllByStudent(User user);

    List<SportScore> findAllByStudentAndTimeTableInfo(User user, TimeTableInfo timeTableInfo);

    List<SportScore> findAllByStudentAndSportSection(User user, SportSection sportSection);

    SportScore save(SportScore sportScore);

    void delete(SportScore sportScore);
}
