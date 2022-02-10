package me.sa1zer_.sporterbook.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.model.base.BaseEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "api_student_visits")
@Data
public class StudentVisit extends BaseEntity {

    private boolean visit;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToOne()
    @JoinColumn(name = "date")
    private StudentTimeTable date;
}
