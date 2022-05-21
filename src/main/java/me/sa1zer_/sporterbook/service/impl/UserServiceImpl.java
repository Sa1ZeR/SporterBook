package me.sa1zer_.sporterbook.service.impl;

import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.domain.model.enums.Role;
import me.sa1zer_.sporterbook.payload.request.admin.EditUserRequest;
import me.sa1zer_.sporterbook.payload.request.SignUpRequest;
import me.sa1zer_.sporterbook.repository.UserRepository;
import me.sa1zer_.sporterbook.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder
                           ) {
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
    public User findByPrincipal(Principal principal) {
        return findUserByLoginOrEmail(principal.getName(), principal.getName());
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with id %s not found!", id)));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).toList();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

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

        user.getRoles().add(Role.USER);
        user.setActive(true);

        return save(user);
    }

    @Override
    public List<User> getChildren(User parent) {
        return new ArrayList<>(parent.getChildren());
    }

    @Override
    public User updateByRequest(EditUserRequest request, User... userArgs) {
        User user = userArgs[0];

        user.setFistName(request.getFirstNmae());
        user.setLastName(request.getLastName());
        user.setPatronymic(request.getPatronymic());
        user.setLogin(request.getLogin());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setSex(request.getSex());
        user.setRoles(request.getRoles());

        return save(user);
    }
}
