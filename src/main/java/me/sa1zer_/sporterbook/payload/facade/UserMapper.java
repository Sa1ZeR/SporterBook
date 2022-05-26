package me.sa1zer_.sporterbook.payload.facade;

import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.dto.AdminUserDto;
import me.sa1zer_.sporterbook.payload.dto.IUserDto;
import me.sa1zer_.sporterbook.payload.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, IUserDto> {

    @Override
    public IUserDto map(User u) {
        UserDto dto = new UserDto();

        dto.setId(u.getId());
        dto.setLogin(u.getLogin());
        dto.setEmail(u.getEmail());
        dto.setFirstName(u.getFistName());
        dto.setLastName(u.getLastName());
        dto.setPatronymic(u.getPatronymic());
        dto.setPhone(u.getPhone());
        dto.setSex(u.getSex());
        dto.setRoles(u.getRoles());
        dto.setBirth(u.getBirth());
        dto.setCreated(u.getCreated());

        return dto;
    }
}
