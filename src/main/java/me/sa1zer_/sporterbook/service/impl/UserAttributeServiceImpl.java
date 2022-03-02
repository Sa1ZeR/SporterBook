package me.sa1zer_.sporterbook.service.impl;

import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.domain.model.UserAttribute;
import me.sa1zer_.sporterbook.domain.model.enums.Role;
import me.sa1zer_.sporterbook.repository.UserAttributeRepository;
import me.sa1zer_.sporterbook.service.UserAttributeService;
import org.springframework.stereotype.Service;

@Service
public class UserAttributeServiceImpl implements UserAttributeService {

    private final UserAttributeRepository attributeRepository;

    public UserAttributeServiceImpl(UserAttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @Override
    public UserAttribute createUserAttribute(User user, Role role) {
        UserAttribute userAttribute = new UserAttribute();

        userAttribute.setUser(user);
        userAttribute.setRole(role);

        return save(userAttribute);
    }

    @Override
    public UserAttribute save(UserAttribute userAttribute) {
        return attributeRepository.save(userAttribute);
    }

    @Override
    public void delete(UserAttribute attribute) {
        attributeRepository.delete(attribute);
    }
}
