package me.sa1zer_.sporterbook.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.domain.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The name of the event that takes place at a certain time.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "api_timetable_category")
@Data
public class TimeTableCategory extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(32)", nullable = false)
    private String name;
}
