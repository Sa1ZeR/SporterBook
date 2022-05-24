package me.sa1zer_.sporterbook.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class UpdateTimeTableRequest {

    private Long id;

    @JsonFormat(pattern = "yyyy.MM.dd HH:mm")
    @NotNull
    private LocalDateTime start;

    @JsonFormat(pattern = "yyyy.MM.dd HH:mm")
    @NotNull
    private LocalDateTime end;
}
