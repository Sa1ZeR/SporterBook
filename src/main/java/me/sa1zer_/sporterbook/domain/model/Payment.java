package me.sa1zer_.sporterbook.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.domain.model.base.BaseEntity;
import me.sa1zer_.sporterbook.domain.model.enums.Role;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "api_payments")
@Data
public class Payment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private SportSection section;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated
    private Role role;

    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;

    private double amount;
}
