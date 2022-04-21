package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.TimeTableCategory;

import java.util.List;

public interface TimeTableCategoryService {

    TimeTableCategory findById(Long id);

    List<TimeTableCategory> findAll();

    TimeTableCategory findByName(String name);

    TimeTableCategory save(TimeTableCategory category);

    void delete(TimeTableCategory category);
}
