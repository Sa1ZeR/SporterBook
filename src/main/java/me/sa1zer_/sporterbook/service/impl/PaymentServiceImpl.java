package me.sa1zer_.sporterbook.service.impl;

import me.sa1zer_.sporterbook.domain.model.Payment;
import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.exception.PaymentNotFoundException;
import me.sa1zer_.sporterbook.repository.PaymentRepository;
import me.sa1zer_.sporterbook.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() ->
                new PaymentNotFoundException(
                        String.format("Payment with id %s not found", id)));
    }

    @Override
    public Payment getById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Payment> findAllByPayed(User user) {
        return paymentRepository.findAllByPayedBy(user);
    }

    @Override
    public List<Payment> findAllByStudent(User user) {
        return paymentRepository.findAllByStudent(user);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> findAllBySection(SportSection section) {
        return paymentRepository.findAllBySection(section);
    }

    @Override
    public List<Payment> findAllBySectionAndStudent(SportSection section, User user) {
        return paymentRepository.findAllBySectionAndStudent(section, user);
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void delete(Payment payment) {
        paymentRepository.delete(payment);
    }
}
