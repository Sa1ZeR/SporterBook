package me.sa1zer_.sporterbook.service.impl;

import me.sa1zer_.sporterbook.domain.model.Room;
import me.sa1zer_.sporterbook.exception.RoomNotFoundException;
import me.sa1zer_.sporterbook.repository.RoomRepository;
import me.sa1zer_.sporterbook.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(() ->
                new RoomNotFoundException(String.format("Room with id %s not found", id)));
    }

    @Override
    public Room findByNum(int num) {
        return roomRepository.findByNum(num).orElseThrow(() ->
                new RoomNotFoundException(String.format("Room with num %s not found", num)));
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void delete(Room room) {
        roomRepository.delete(room);
    }
}
