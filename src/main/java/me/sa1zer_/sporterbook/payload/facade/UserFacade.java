package me.sa1zer_.sporterbook.payload.facade;

import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.dto.UserDto;

public class UserFacade {

    public static UserDto userToUserDto(User u) {
        UserDto dto = new UserDto();

        dto.setLogin(u.getLogin());
        dto.setEmail(u.getEmail());
        dto.setFirstName(u.getFistName());
        dto.setLastName(u.getLastName());
        dto.setPatronymic(u.getPatronymic());
        dto.setPhone(u.getPhone());
        dto.setSex(u.getSex());
        dto.setBirth(u.getBirth());

        return dto;
    }
}
