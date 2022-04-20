package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.Payment;
import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.domain.model.User;

import java.util.List;

public interface PaymentService {

    Payment findById(Long id);

    Payment getById(Long id);

    List<Payment> findAllByPayed(User user);

    List<Payment> findAllByStudent(User user);

    List<Payment> findAll();

    List<Payment> findAllBySection(SportSection section);

    List<Payment> findAllBySectionAndStudent(SportSection section, User user);

    Payment save(Payment payment);

    void delete(Payment payment);
}
