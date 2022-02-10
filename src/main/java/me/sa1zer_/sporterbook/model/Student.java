package me.sa1zer_.sporterbook.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.model.base.BaseHuman;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "api_students")
@Data
public class Student extends BaseHuman {

    private LocalDateTime created;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "spi_students_to_sections", joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "section_id")})
    private Set<SportSection> sections = new HashSet<>();


    @PrePersist
    public void onCreated() {
        created = LocalDateTime.now();
    }
}
