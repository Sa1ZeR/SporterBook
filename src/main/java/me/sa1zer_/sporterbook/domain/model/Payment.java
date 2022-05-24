package me.sa1zer_.sporterbook.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.domain.model.base.BaseEntity;
import me.sa1zer_.sporterbook.domain.model.enums.Role;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Payment of sections for members.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "api_payments")
@Data
public class Payment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private SportSection section;

    @ManyToOne
    @JoinColumn(name = "payed_by_id", nullable = false)
    private User payedBy;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    private double amount;
}
