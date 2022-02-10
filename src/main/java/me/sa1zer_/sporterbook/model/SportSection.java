package me.sa1zer_.sporterbook.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.model.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Trainer> trainers = new HashSet<>();

    @Column(columnDefinition = "DECIMAL(10,2)")
    private double price;
}
