package me.sa1zer_.sporterbook.api.admin;

import me.sa1zer_.sporterbook.domain.model.TimeTableCategory;
import me.sa1zer_.sporterbook.payload.request.admin.UpdateTimeTableCategoryRequest;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.service.TimeTableCategoryService;
import me.sa1zer_.sporterbook.utils.HttpUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/admin/timetablecategory/")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminTimeTableCategoryController {

    private final TimeTableCategoryService timeTableCategoryService;

    public AdminTimeTableCategoryController(TimeTableCategoryService timeTableCategoryService) {
        this.timeTableCategoryService = timeTableCategoryService;
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@Valid @RequestBody
                                                UpdateTimeTableCategoryRequest request,
                                    BindingResult result) {
        ResponseEntity<Object> res = HttpUtils.validBindingResult(result);
        if(!ObjectUtils.isEmpty(res)) return res;

        if(request.getId() == null)
            timeTableCategoryService.create(request.getName());
        else {
            TimeTableCategory category = timeTableCategoryService.findById(request.getId());
            category.setName(request.getName());
            timeTableCategoryService.save(category);
        }

        return ResponseEntity.ok(new MessageResponse("Категория успешно сохранена"));
    }
}
