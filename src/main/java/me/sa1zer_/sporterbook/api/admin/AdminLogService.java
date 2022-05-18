package me.sa1zer_.sporterbook.api.admin;

import me.sa1zer_.sporterbook.domain.model.enums.LogType;
import me.sa1zer_.sporterbook.payload.facade.LogMapper;
import me.sa1zer_.sporterbook.service.LogService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/log")
public class AdminLogService {

    private final LogService logService;
    private final LogMapper logMapper;

    public AdminLogService(LogService logService, LogMapper logMapper) {
        this.logService = logService;
        this.logMapper = logMapper;
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll(@RequestParam int page,@RequestParam int num,
                                    @RequestParam(required = false) LogType logType) {
        if(logType == null) {
            return ResponseEntity.ok(logService.findALl(PageRequest.of(page, num,
                    Sort.by("id").descending())).stream()
                    .map(logMapper::map).toList());
        }
        return ResponseEntity.ok(logService.findALlByType(logType, PageRequest.of(page, num,
                Sort.by("id").descending())).stream()
                .map(logMapper::map).toList());
    }
}
