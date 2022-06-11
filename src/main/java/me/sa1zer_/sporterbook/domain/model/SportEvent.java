package me.sa1zer_.sporterbook.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.domain.model.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Events or classes that take place in the section.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "api_sport_events")
@Data
public class SportEvent extends BaseEntity {

    @Column(nullable = false)
    private String name;

//    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
//    @JoinTable(name = "api_sport_event_members", joinColumns = {@JoinColumn(name = "sport_event_id")},
//            inverseJoinColumns = {@JoinColumn(name = "student_id")})
//    private Set<User> members = new HashSet<>();

    @ManyToOne()
    @JoinColumn(nullable = false)
    private User student;

    @Column(nullable = false)
    private int result;

    @Column(nullable = false)
    private int maxResult;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User trainer;

    @OneToOne()
    @JoinColumn(name = "tti_id")
    private TimeTableInfo timeTableInfo;
}
