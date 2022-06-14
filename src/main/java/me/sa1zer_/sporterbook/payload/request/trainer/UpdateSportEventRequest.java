package me.sa1zer_.sporterbook.payload.request.trainer;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class UpdateSportEventRequest {

    private Long id;
    @NotNull(message = "Id студента не может быть пустым")
    private Long student;

    @NotNull(message = "Id секции не может быть пустым")
    private Long section;

    @NotNull(message = "Id расписания не может быть пустым")
    private Long tti;

    private String name;
    @Positive(message = "Результат должен быть больше 0")
    private int result;
    @Min(value = 5, message = "Максимальный результат не может быть меньше 5")
    private int maxResult;
}
