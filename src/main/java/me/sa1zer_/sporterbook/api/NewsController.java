package me.sa1zer_.sporterbook.api;

import me.sa1zer_.sporterbook.payload.facade.NewsMapper;
import me.sa1zer_.sporterbook.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news/")
public class NewsController {

    private final NewsService newsService;
    private final NewsMapper newsMapper;

    public NewsController(NewsService newsService, NewsMapper newsMapper) {
        this.newsService = newsService;
        this.newsMapper = newsMapper;
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll(@RequestParam(required = false) String title) {
        if(StringUtils.hasText(title))
            return ResponseEntity.ok(newsService.findALlSimilar(title).stream().map(newsMapper::map).toList());
        return ResponseEntity.ok(newsService.findALl().stream().map(newsMapper::map).toList());
    }

    @GetMapping("get")
    public ResponseEntity<?> getAll(@RequestParam Long id) {
        return ResponseEntity.ok(newsMapper.map(newsService.findById(id)));
    }
}
