package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.model.User;
import me.sa1zer_.sporterbook.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByLoginOrEmail(String login, String email) {
        return userRepository.findByLoginOrEmail(login, email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with %s or %s not found!", login, email)));
    }

    public User getUserByLoginOrEmail(String login, String email) {
        return userRepository.findByLoginOrEmail(login, email).orElse(null);
    }
}
