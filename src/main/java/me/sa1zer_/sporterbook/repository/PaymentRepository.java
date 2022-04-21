package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.Payment;
import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByPayedBy(User user);

    List<Payment> findAllByStudent(User user);

    List<Payment> findAllBySection(SportSection section);

    List<Payment> findAllBySectionAndStudent(SportSection section, User user);
}
