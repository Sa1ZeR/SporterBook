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
@Table(name = "api_timetable")
@Data
public class TimeTable extends BaseEntity {

    @Column(name = "date_start", nullable = false)
    private LocalDateTime dateStart;
    @Column(name = "date_end", nullable = false)
    private LocalDateTime dateEnd;

    @OneToMany(mappedBy = "timeTable", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<TimeTableInfo> timeTableInfos = new HashSet<>();
}
