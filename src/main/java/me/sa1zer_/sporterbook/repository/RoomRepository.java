package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByNum(int num);
}
