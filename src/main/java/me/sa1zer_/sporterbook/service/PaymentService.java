package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.Payment;
import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.domain.model.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface PaymentService {

    Payment findById(Long id);

    Payment getById(Long id);

    List<Payment> findAllByPayed(User user);

    List<Payment> findAllByStudent(User user);

    List<Payment> findAllByStudentAndStartDateGreaterThan(User user, LocalDateTime s);

    List<Payment> findAll();

    List<Payment> findAllBySection(SportSection section);

    List<Payment> findAllBySectionAndStudent(SportSection section, User user);

    List<Payment> findAllBySections(Collection<SportSection> sections, LocalDateTime start);

    Payment save(Payment payment);

    void delete(Payment payment);
}
