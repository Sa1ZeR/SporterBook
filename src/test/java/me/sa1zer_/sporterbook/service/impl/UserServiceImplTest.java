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
        user.setPassword(passwordEncoder.encode("12345678"));
        user.setBirth(LocalDateTime.now());

        doReturn(java.util.Optional.of(user)).when(userRepository)
                .findByLoginOrEmail("Sa1ZeR_", "test@mail.ru");
        User find = userService.findUserByLoginOrEmail("Sda1ZeR_", "tdest@mail.ru");
        assertAll(
                () -> assertEquals("Sa1ZeR_", find.getLogin()),
                ()-> assertEquals("test@mail.ru", find.getEmail())
        );
    }

    @Test
    void findById() {
        User user = new User();
        user.setId(1L);
        user.setFistName("FirstName");
        user.setLastName("LastName");
        user.setPatronymic("Patr");
        user.setLogin("Sa1ZeR_");
        user.setEmail("test@mail.ru");
        user.setPhone("+79033333333");
        user.setPassword(passwordEncoder.encode("12345678"));
        user.setBirth(LocalDateTime.now());
        user.setActive(true);

//        when(userRepository.findById(Mockito.any(Long.class)))
//                .thenReturn(java.util.Optional.of(user));

        doReturn(java.util.Optional.of(user)).when(userRepository)
                .findById(1L);

        User find = userService.findById(3L);

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

        User user1 = new User();
        user1.setFistName("FirstName");
        user1.setLastName("LastName");
        user1.setPatronymic("Patr");
        user1.setLogin("Sa1ZeR_");
        user1.setEmail("test@mail.ru");
        user1.setPassword(passwordEncoder.encode("12345678"));

        when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        userService.save(user);

        userService.delete(user1);
        verify(userRepository, times(1)).delete(user);
    }
}