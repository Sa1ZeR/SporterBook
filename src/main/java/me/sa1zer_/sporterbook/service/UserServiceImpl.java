package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.model.User;
import me.sa1zer_.sporterbook.payload.request.SignUpRequest;
import me.sa1zer_.sporterbook.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserByLoginOrEmail(String login, String email) {
        return userRepository.findByLoginOrEmail(login, email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with %s or %s not found!",
                        login, email)));
    }

    public User getUserByLoginOrEmail(String login, String email) {
        return userRepository.findByLoginOrEmail(login, email).orElse(null);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with id %s not found!", id)));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    //todo refactoring to builder pattern
    @Override
    public User createUserFromRequest(SignUpRequest request) {
        User user = new User();

        user.setFistName(request.getFirstName());
        user.setLastName(request.getLatName());
        user.setPatronymic(request.getPatronymic());
        user.setLogin(request.getLogin());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setSex(request.getSex());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setBirth(request.getBirth().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());

        user.setActive(true);

        return user;
    }
}
