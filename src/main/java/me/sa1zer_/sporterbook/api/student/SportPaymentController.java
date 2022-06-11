package me.sa1zer_.sporterbook.api.student;

import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.facade.PaymentMapper;
import me.sa1zer_.sporterbook.service.PaymentService;
import me.sa1zer_.sporterbook.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/students/payment/")
@PreAuthorize("hasAuthority('STUDENT')")
public class SportPaymentController {

    private final UserService userService;
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    public SportPaymentController(UserService userService, PaymentService paymentService,
                                  PaymentMapper paymentMapper) {
        this.userService = userService;
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;
    }

    @GetMapping("getPayments")
    public ResponseEntity<?> getPayments(Principal principal) {
        User student = userService.findByPrincipal(principal);
        return ResponseEntity.ok(paymentService.findAllByStudent(student)
                .stream().map(paymentMapper::map).toList());
    }
}
