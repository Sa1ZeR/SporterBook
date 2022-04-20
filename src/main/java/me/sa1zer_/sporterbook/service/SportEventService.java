package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.SportEvent;
import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;

public interface SportEventService {

    SportEvent findById(Long id);

    SportEvent findByName(String name);

    SportEvent findBytimeTableInfo(TimeTableInfo timeTableInfo);

    SportEvent save(SportEvent sportEvent);

    void delete(SportEvent sportEvent);
}
