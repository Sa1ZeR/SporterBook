package me.sa1zer_.sporterbook.payload.request.admin;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

/**
 * Data about edited News information.
 */
@Data
public class UpdateNewsRequest {

    private Long id;
    @Size(min = 3, max = 255)
    private String title;

    private String text;
    private MultipartFile img;
}
