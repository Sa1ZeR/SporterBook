package me.sa1zer_.sporterbook.service.impl;

import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.repository.UserRepository;
import me.sa1zer_.sporterbook.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Test
    void findUserByLoginOrEmail() {
        User user = new User();
        user.setFistName("FirstName");
        user.setLastName("LastName");
        user.setPatronymic("Patr");
        user.setLogin("Sa1ZeR_");
        user.setEmail("test@mail.ru");
        user.setPhone("+79033333333");
        user.setPassword(passwordEncoder.encode("12345678"));
        user.setBirth(LocalDateTime.now());

        when(userRepository.findByLoginOrEmail(Mockito.any(String.class), Mockito.any(String.class)))
                .thenReturn(java.util.Optional.of(user));

        User find = userService.findUserByLoginOrEmail("Sa1ZeR_", "test@mail.ru");
        assertAll(
                () -> assertEquals("Sa1ZeR_", find.getLogin()),
                ()-> assertEquals("test@mail.ru", find.getEmail())
        );
    }

    @Test
    void findById() {
        User user = new User();

        user.setFistName("FirstName");
        user.setLastName("LastName");
        user.setPatronymic("Patr");
        user.setLogin("Sa1ZeR_");
        user.setEmail("test@mail.ru");
        user.setPhone("+79033333333");
        user.setSex((short) 0);
        user.setPassword(passwordEncoder.encode("12345678"));
        user.setBirth(LocalDateTime.now());
        user.setActive(true);

        when(userRepository.findById(Mockito.any(Long.class)))
                .thenReturn(java.util.Optional.of(user));

        User find = userService.findById(1L);

        assertEquals(1L, find.getId());

    }

    @Test
    void save() {
        User user = new User();
        user.setFistName("FirstName");
        user.setLastName("LastName");
        user.setPatronymic("Patr");
        user.setLogin("Sa1ZeR_");
        user.setEmail("test@mail.ru");
        user.setPassword(passwordEncoder.encode("12345678"));
        user.setBirth(LocalDateTime.now());

        when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        User save = userService.save(user);

        assertEquals(user, save);
    }

    @Test
    void delete() {
        User user = new User();
        user.setFistName("FirstName");
        user.setLastName("LastName");
        user.setPatronymic("Patr");
        user.setLogin("Sa1ZeR_");
        user.setEmail("test@mail.ru");
        user.setPassword(passwordEncoder.encode("12345678"));
        user.setBirth(LocalDateTime.now());

        when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        userService.save(user);

        userService.delete(user);
        verify(userRepository, times(1)).delete(user);
    }
}