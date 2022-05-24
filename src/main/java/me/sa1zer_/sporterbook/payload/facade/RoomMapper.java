package me.sa1zer_.sporterbook.payload.facade;

import lombok.Data;
import me.sa1zer_.sporterbook.domain.model.Room;
import me.sa1zer_.sporterbook.payload.dto.RoomDto;
import org.springframework.stereotype.Component;

@Data
@Component
public class RoomMapper implements Mapper<Room, RoomDto> {

    @Override
    public RoomDto map(Room from) {
        RoomDto roomDto = new RoomDto();

        roomDto.setId(from.getId());
        roomDto.setNum(from.getNum());

        return roomDto;
    }
}
