package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.SportEvent;
import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SportEventRepository extends JpaRepository<SportEvent, Long> {

    Optional<SportEvent> findByName(String name);

    Optional<SportEvent> findBytimeTableInfo(TimeTableInfo timeTableInfo);
}
