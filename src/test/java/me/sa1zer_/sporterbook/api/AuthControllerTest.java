package me.sa1zer_.sporterbook.api;

import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.repository.UserRepository;
import me.sa1zer_.sporterbook.service.UserAttributeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserAttributeService userAttributeService;

    @Test
    void signIn() throws Exception {
        when(userRepository.findByLoginOrEmail("Sa1ZeR_", "Sa1ZeR_"))
                .thenReturn(java.util.Optional.of(new User()));
        this.mockMvc.perform(post("/api/auth/signin")
            .content("\"login\":\"Sa1ZeR_\",\"password\":\"12345678\"")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void signUp() {
    }
}