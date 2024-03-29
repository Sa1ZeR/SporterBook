package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.Payment;
import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByPayedBy(User user);

    List<Payment> findAllByStudent(User user);

    @Query("select p from Payment p where p.section in :sections and p.startDate >= :start and p.student = :student")
    List<Payment> findAllByStudentAndStartDateGreaterThan(@Param("student") User student,
                                                          @Param("sections") Collection<SportSection> sections,
                                                          @Param("start") LocalDateTime start);

    List<Payment> findAllBySection(SportSection section);

    List<Payment> findAllBySectionAndStudent(SportSection section, User user);

    //custom
    @Query("select p from Payment p where p.section in :sections and p.startDate >= :start")
    List<Payment> findAllBySections(@Param("sections") Collection<SportSection> sections,
                                    @Param("start")LocalDateTime start);
}
