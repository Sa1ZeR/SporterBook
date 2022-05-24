package me.sa1zer_.sporterbook.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.domain.model.base.BaseEntity;

import javax.persistence.*;

/**
 * Information for scheduling for the section.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "api_timetable_info")
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
}
