package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.request.SignUpRequest;

import java.util.List;

public interface UserService {

    User findUserByLoginOrEmail(String login, String email);

    User getUserByLoginOrEmail(String login, String email);

    User findById(Long id);

    List<User> findAll();

    User save(User user);

    void delete(User user);

    User createUserFromRequest(SignUpRequest request);
}
