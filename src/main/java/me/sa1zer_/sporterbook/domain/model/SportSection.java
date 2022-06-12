package me.sa1zer_.sporterbook.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.domain.model.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Information about the sections and their members.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "api_sport_sections")
public class SportSection extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(32)", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String desc;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "api_trainers_to_sections", joinColumns = {@JoinColumn(name = "sid")},
            inverseJoinColumns = {@JoinColumn(name = "tid")})
    private Set<User> trainers = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "api_students_to_sections", joinColumns = {@JoinColumn(name = "section_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private Set<User> students = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "api_requests_to_sections", joinColumns = {@JoinColumn(name = "s_id")},
            inverseJoinColumns = {@JoinColumn(name = "u_id")})
    private Set<User> requests = new HashSet<>();

    @Column(columnDefinition = "DECIMAL(10,2)")
    private double price;
}
