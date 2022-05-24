package me.sa1zer_.sporterbook.payload.request.admin;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Data about edited section information.
 */
@Data
public class SportSectionRequest {

    private Long id;
    @NotBlank(message = "Имя секции не может быть пустым")
    @Size(min = 3, max = 30)
    private String name;
    private String desc;

    @DecimalMax(value = "100000.0")
    @DecimalMin("0.0")
    private double price;
}
