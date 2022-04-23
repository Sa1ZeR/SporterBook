package me.sa1zer_.sporterbook.service.impl;

import me.sa1zer_.sporterbook.domain.model.TimeTableCategory;
import me.sa1zer_.sporterbook.exception.TimetableCategoryNotFound;
import me.sa1zer_.sporterbook.repository.TimeTableCategoryRepository;
import me.sa1zer_.sporterbook.service.TimeTableCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeTableCategoryServiceImpl implements TimeTableCategoryService {

    private TimeTableCategoryRepository categoryRepository;

    @Override
    public TimeTableCategory findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new TimetableCategoryNotFound(String.format(
                        "TimeTable Category with id %s not found!", id)));
    }

    @Override
    public List<TimeTableCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public TimeTableCategory findByName(String name) {
        return categoryRepository.findByName(name).orElseThrow(() ->
                new TimetableCategoryNotFound(String.format(
                        "TimeTable Category with name %s not found!", name)));
    }

    @Override
    public TimeTableCategory create(String name) {
        TimeTableCategory timeTableCategory = new TimeTableCategory();

        timeTableCategory.setName(name);

        return save(timeTableCategory);
    }

    @Override
    public TimeTableCategory save(TimeTableCategory category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(TimeTableCategory category) {
        categoryRepository.delete(category);
    }
}
