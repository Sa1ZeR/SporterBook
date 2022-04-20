package me.sa1zer_.sporterbook.service.impl;

import me.sa1zer_.sporterbook.domain.model.SportEvent;
import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.exception.SportEventNotFound;
import me.sa1zer_.sporterbook.repository.SportEventRepository;
import me.sa1zer_.sporterbook.service.SportEventService;
import org.springframework.stereotype.Service;

@Service
public class SportEventServiceImpl implements SportEventService {

    private final SportEventRepository sportEventRepository;

    public SportEventServiceImpl(SportEventRepository sportEventRepository) {
        this.sportEventRepository = sportEventRepository;
    }

    @Override
    public SportEvent findById(Long id) {
        return sportEventRepository.findById(id).orElseThrow(() ->
                new SportEventNotFound(String.format("SportEvent with id %s not found", id)));
    }

    @Override
    public SportEvent findByName(String name) {
        return sportEventRepository.findByName(name).orElseThrow(() ->
                new SportEventNotFound(String.format(
                        "SportEvent with name %s not found", name)));
    }

    @Override
    public SportEvent findBytimeTableInfo(TimeTableInfo timeTableInfo) {
        return sportEventRepository.findBytimeTableInfo(timeTableInfo).orElseThrow(() ->
                new SportEventNotFound(String.format(
                        "SportEvent with info id %s not found", timeTableInfo.getId())));
    }

    @Override
    public SportEvent save(SportEvent sportEvent) {
        return sportEventRepository.save(sportEvent);
    }

    @Override
    public void delete(SportEvent sportEvent) {
        sportEventRepository.delete(sportEvent);
    }
}
