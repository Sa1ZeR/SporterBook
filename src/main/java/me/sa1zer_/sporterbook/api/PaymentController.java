package me.sa1zer_.sporterbook.api;

import me.sa1zer_.sporterbook.service.LogService;
import me.sa1zer_.sporterbook.service.PaymentService;
import me.sa1zer_.sporterbook.service.UserService;

public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;
    private final LogService logService;

    public PaymentController(PaymentService paymentService, UserService userService,
                             LogService logService) {
        this.paymentService = paymentService;
        this.userService = userService;
        this.logService = logService;
    }
}
