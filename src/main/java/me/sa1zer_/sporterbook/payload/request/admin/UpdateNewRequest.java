package me.sa1zer_.sporterbook.payload.request.admin;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

@Data
public class UpdateNewRequest {

    private Long id;
    @Size(min = 3, max = 255)
    private String title;

    private String text;
    private MultipartFile img;
}
