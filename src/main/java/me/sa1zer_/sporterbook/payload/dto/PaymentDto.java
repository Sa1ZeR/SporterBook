package me.sa1zer_.sporterbook.payload.dto;

import lombok.Builder;
import lombok.Data;
import me.sa1zer_.sporterbook.domain.model.User;

import java.time.LocalDateTime;

@Data
@Builder
public class PaymentDto {

    private Long id;
    private UserDto paymentBy;
    private UserDto student;
    private LocalDateTime start;
    private LocalDateTime end;
    private double amount;
}
