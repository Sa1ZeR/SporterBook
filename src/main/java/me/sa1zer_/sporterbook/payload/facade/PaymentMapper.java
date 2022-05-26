package me.sa1zer_.sporterbook.payload.facade;

import me.sa1zer_.sporterbook.domain.model.Payment;
import me.sa1zer_.sporterbook.payload.dto.PaymentDto;
import me.sa1zer_.sporterbook.payload.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper implements Mapper<Payment, PaymentDto> {

    private final UserMapper userMapper;

    public PaymentMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public PaymentDto map(Payment from) {
        return PaymentDto.builder()
                .id(from.getId())
                .paymentBy((UserDto) userMapper.map(from.getPayedBy()))
                .student((UserDto) userMapper.map(from.getStudent()))
                .start(from.getStartDate())
                .end(from.getEndDate())
                .amount(from.getAmount())
                .build();
    }
}
