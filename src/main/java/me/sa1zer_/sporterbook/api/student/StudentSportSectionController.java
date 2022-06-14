package me.sa1zer_.sporterbook.api.student;

import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.facade.SportSectionMapper;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.service.LogService;
import me.sa1zer_.sporterbook.service.SportSectionService;
import me.sa1zer_.sporterbook.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/students/sections/")
//@PreAuthorize("hasAuthority('STUDENT')")
public class StudentSportSectionController {

    private final SportSectionService sportSectionService;
    private final UserService userService;
    private final SportSectionMapper sectionMapper;
    private final LogService logService;

    public StudentSportSectionController(SportSectionService sportSectionService, UserService userService,
                                         SportSectionMapper sectionMapper, LogService logService) {
        this.sportSectionService = sportSectionService;
        this.userService = userService;
        this.sectionMapper = sectionMapper;
        this.logService = logService;
    }

    @PostMapping("sendRequest/{sId}")
    public ResponseEntity<?> sendRequest(@PathVariable String sId, Principal principal)  {
        SportSection sportSection = sportSectionService.findById(Long.parseLong(sId)); //search section in db
        User user = userService.findByPrincipal(principal); //get user from session

        sportSection.getRequests().add(user); //add request

        sportSectionService.save(sportSection); //save in db

        //return response
        return ResponseEntity.ok(new MessageResponse("Запрос на вступление успешно отправлен!"));
    }


    @GetMapping("getAll")
    public ResponseEntity<?> getAll(Principal principal) {
        User user = userService.findByPrincipal(principal); //get user from session

        //return response
        return ResponseEntity.ok(user.getSections()
                .stream().map(sectionMapper::map).toList());
    }
}
