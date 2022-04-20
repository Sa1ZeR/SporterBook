package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    Room findById(Long id);

    Room findByNum(int num);

    List<Room> findAll();


}
