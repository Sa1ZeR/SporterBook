package me.sa1zer_.sporterbook.api.trainer;

import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.facade.PaymentMapper;
import me.sa1zer_.sporterbook.service.PaymentService;
import me.sa1zer_.sporterbook.service.UserService;
import org.hibernate.type.descriptor.java.LocalDateTimeJavaDescriptor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/trainer/payment/")
@PreAuthorize("hasAuthority('TRAINER')")
public class TrainerPaymentController {

    private final UserService userService;
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    public TrainerPaymentController(UserService userService, PaymentService paymentService,
                                    PaymentMapper paymentMapper) {
        this.userService = userService;
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;
    }

    @GetMapping("getStudentsPayments")
    public ResponseEntity<?> getPayedStudents(@RequestParam(name = "startDate")
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                             pattern = "yyyy.MM.dd")
                                              LocalDate startDate, Principal principal) {
        User trainer = userService.findByPrincipal(principal);
        return ResponseEntity.ok(paymentService.findAllBySections(trainer.getTrainersSections(),
                LocalDateTime.of(startDate, LocalTime.MIN))
                .stream().map(paymentMapper::map).toList());
    }

    @GetMapping("getStudentSectionPayments")
    public ResponseEntity<?> getPayedStudentSection(@RequestParam(name = "startDate")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                         pattern = "yyyy.MM.dd")
                                                         LocalDate startDate,
                                                @RequestParam(name="sId") Long sId, Principal principal) {
        User trainer = userService.findByPrincipal(principal);
        User student = userService.findById(sId);
        return ResponseEntity.ok(paymentService.findAllByStudentAndStartDateGreaterThan(student,
                        trainer.getSections(), LocalDateTime.of(startDate, LocalTime.MIN))
                .stream().map(paymentMapper::map).toList());
    }
}