package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.TimeTableCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeTableCategoryRepository extends JpaRepository<TimeTableCategory, Long> {

    Optional<TimeTableCategory> findByName(String name);
}
