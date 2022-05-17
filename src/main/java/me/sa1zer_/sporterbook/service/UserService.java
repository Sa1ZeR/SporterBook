package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.handler.ServiceUpdateByRequestHandler;
import me.sa1zer_.sporterbook.payload.request.EditUserRequest;
import me.sa1zer_.sporterbook.payload.request.SignUpRequest;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface UserService extends ServiceUpdateByRequestHandler<User, EditUserRequest> {

    User findUserByLoginOrEmail(String login, String email);

    User getUserByLoginOrEmail(String login, String email);

    User findByPrincipal(Principal principal);

    User findById(Long id);

    List<User> findAll();

    List<User> findAll(Pageable pageable);

    User save(User user);

    void delete(User user);

    User createUserFromRequest(SignUpRequest request);

    List<User> getChildren(User parent);
}
