package me.sa1zer_.sporterbook.api.student;

import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.facade.SportSectionMapper;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.service.SportSectionService;
import me.sa1zer_.sporterbook.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/students/sections/")
@PreAuthorize("hasAuthority('STUDENT')")
public class StudentSportSectionController {

    private final SportSectionService sportSectionService;
    private final UserService userService;
    private final SportSectionMapper sectionMapper;

    public StudentSportSectionController(SportSectionService sportSectionService, UserService userService,
                                         SportSectionMapper sectionMapper) {
        this.sportSectionService = sportSectionService;
        this.userService = userService;
        this.sectionMapper = sectionMapper;
    }

    @PostMapping("sendRequest/{sId}")
    public ResponseEntity<?> sendRequest(@PathVariable String sId, Principal principal)  {
        SportSection sportSection = sportSectionService.findById(Long.parseLong(sId));
        User user = userService.findByPrincipal(principal);

        sportSection.getRequests().add(user);

        sportSectionService.save(sportSection);

        return ResponseEntity.ok(new MessageResponse("Запрос на вступление успешно отправлен!"));
    }
}
