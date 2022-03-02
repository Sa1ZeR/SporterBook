package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.domain.model.UserAttribute;
import me.sa1zer_.sporterbook.domain.model.enums.Role;

public interface UserAttributeService {

    UserAttribute createUserAttribute(User user, Role role);

    UserAttribute save(UserAttribute userAttribute);

    void delete(UserAttribute attribute);
}
