package me.sa1zer_.sporterbook.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.model.base.BaseEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
public class TimeTableInfo extends BaseEntity {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "timetable_id", nullable = false, referencedColumnName = "id")
    private TimeTable timeTable;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "section_id", nullable = false, referencedColumnName = "id")
    private SportSection section;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "room_id", nullable = false, referencedColumnName = "id")
    private Room room;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    private TimeTableCategory category;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //for who

    //todo trainers, but column user_id must be trainer. how???
}
