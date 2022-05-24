package me.sa1zer_.sporterbook.api.admin;

import me.sa1zer_.sporterbook.domain.model.News;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.request.admin.UpdateNewsRequest;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.service.NewsService;
import me.sa1zer_.sporterbook.service.UserService;
import me.sa1zer_.sporterbook.utils.HttpUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/admin/news/")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminNewsController {

    private final NewsService newsService;
    private final UserService userService;

    public AdminNewsController(NewsService newsService, UserService userService) {
        this.newsService = newsService;
        this.userService = userService;
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@Valid UpdateNewsRequest request, BindingResult result,
                                    Principal pr) {
        ResponseEntity<Object> response = HttpUtils.validBindingResult(result);
        if(!ObjectUtils.isEmpty(response)) return response;

        User author = userService.findByPrincipal(pr);
        newsService.updateByRequest(request, author);

        return ResponseEntity.ok(new MessageResponse("Новость успешно обновлена"));
    }

    @PostMapping("delete/{nId}")
    public ResponseEntity<?> delete(@PathVariable String nId) {
        News news = newsService.findById(Long.parseLong(nId));
        newsService.delete(news);

        return ResponseEntity.ok(new MessageResponse("Новость удалена!"));
    }
}
