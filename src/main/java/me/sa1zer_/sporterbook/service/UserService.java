package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.model.User;
import me.sa1zer_.sporterbook.payload.request.SignUpRequest;

public interface UserService{

    User findUserByLoginOrEmail(String login, String email);

    User getUserByLoginOrEmail(String login, String email);

    User findById(Long id);

    User save(User user);

    void delete(User user);

    User createUserFromRequest(SignUpRequest request);
}
