package me.sa1zer_.sporterbook.api;

import me.sa1zer_.sporterbook.payload.facade.SportSectionMapper;
import me.sa1zer_.sporterbook.service.LogService;
import me.sa1zer_.sporterbook.service.SportSectionService;
import me.sa1zer_.sporterbook.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class SportSectionController {

    private final SportSectionService sportSectionService;
    private final UserService userService;
    private final LogService logService;
    private final SportSectionMapper mapper;

    public SportSectionController(SportSectionService sportSectionService,
                                  UserService userService,
                                  LogService logService, SportSectionMapper mapper) {
        this.sportSectionService = sportSectionService;
        this.userService = userService;
        this.logService = logService;
        this.mapper = mapper;
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll(int page, int num) {
        return ResponseEntity.ok(sportSectionService.findAll(
                PageRequest.of(page, num, Sort.by("id").descending()))
                .stream().map(mapper::map).toList());
    }
}
