package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.SportEvent;
import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.domain.model.User;

import java.util.List;

public interface SportEventService {

    SportEvent findById(Long id);

    SportEvent findByName(String name);

    SportEvent findBytimeTableInfo(TimeTableInfo timeTableInfo);

    List<SportEvent> findAllByStudent(User user);

    List<SportEvent> findAllByStudentAndTimeTableInfo(User user, TimeTableInfo timeTableInfo);

    SportEvent save(SportEvent sportEvent);

    void delete(SportEvent sportEvent);
}
