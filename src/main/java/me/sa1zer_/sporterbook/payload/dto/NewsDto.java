package me.sa1zer_.sporterbook.payload.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsDto {

    private Long id;
    private String title;
    private String text;
    private String img;
    private String author;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    protected LocalDateTime date;
}
