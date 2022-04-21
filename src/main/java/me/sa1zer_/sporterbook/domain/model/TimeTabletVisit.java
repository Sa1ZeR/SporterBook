package me.sa1zer_.sporterbook.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.domain.model.base.BaseEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "api_user_visits")
@Data
public class TimeTabletVisit extends BaseEntity {

    private boolean visit;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User student;

    @OneToOne()
    @JoinColumn(name = "date_id")
    private TimeTableInfo date;
}
