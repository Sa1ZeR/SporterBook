package me.sa1zer_.sporterbook.api.admin;

import me.sa1zer_.sporterbook.domain.model.News;
import me.sa1zer_.sporterbook.domain.model.Room;
import me.sa1zer_.sporterbook.payload.request.admin.UpdateRoomRequest;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.service.RoomService;
import me.sa1zer_.sporterbook.utils.HttpUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/room/")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminRoomController {

    private final RoomService roomService;

    public AdminRoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping("update")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateRoomRequest request, BindingResult result) {
        ResponseEntity<Object> res = HttpUtils.validBindingResult(result);
        if(!ObjectUtils.isEmpty(res)) return res;

        Room room;
        if(request.getId() == null) {
            room = new Room();
            room.setNum(request.getNum());
        } else {
            room = roomService.findById(request.getId());
            room.setNum(request.getNum());
        }

        roomService.save(room);

        return ResponseEntity.ok(new MessageResponse("Зал успешно сохранен!"));
    }

    @PostMapping("delete/{nId}")
    public ResponseEntity<?> delete(@PathVariable String nId) {
        Room room = roomService.findById(Long.parseLong(nId));
        roomService.delete(room);

        return ResponseEntity.ok(new MessageResponse("Зал успешно удален!"));
    }
}
