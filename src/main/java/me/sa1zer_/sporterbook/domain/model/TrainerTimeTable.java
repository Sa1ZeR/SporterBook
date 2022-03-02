package me.sa1zer_.sporterbook.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.domain.model.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "api_trainer_timeTable")
@Data
@Deprecated
public class TrainerTimeTable extends BaseEntity {

    @Column(name = "date_start")
    private LocalDateTime dateStart;
    @Column(name = "date_end")
    private LocalDateTime dateEnd;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "api_trainer_to_timetable", joinColumns = {@JoinColumn(name = "trainer_time_table_id")},
            inverseJoinColumns = {@JoinColumn(name = "trainer_id")})
    private Set<Trainer> studentsTimeTable = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "api_sections_to_timetable", joinColumns = {@JoinColumn(name = "timetable_id")},
            inverseJoinColumns = {@JoinColumn(name = "section_id")})
    private Set<SportSection> section = new HashSet<>();

    //todo where

}
