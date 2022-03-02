package me.sa1zer_.sporterbook.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.domain.model.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "api_student_timetable")
@Data
@Deprecated
public class StudentTimeTable extends BaseEntity {

    @Column(name = "date_start")
    private LocalDateTime dateStart;
    @Column(name = "date_end")
    private LocalDateTime dateEnd;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "timetabe_id")
    private Set<StudentSectionRoom> ssr;
}
