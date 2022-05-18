package me.sa1zer_.sporterbook.payload.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import me.sa1zer_.sporterbook.domain.model.enums.LogType;

import java.time.LocalDateTime;

@Data
public class LogDto {

    private String user;
    private String message;
    private LogType logType;
    @JsonFormat(pattern = "HH:mm:ss dd:MM:yyyy")
    private LocalDateTime date;
}
