package me.sa1zer_.sporterbook.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "api_students_to_timetable")
@Data
@Deprecated
public class StudentSectionRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private SportSection section;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
