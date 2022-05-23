package me.sa1zer_.sporterbook.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.domain.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The rooms in which the sections are held.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "api_rooms")
@Data
public class Room extends BaseEntity {

    @Column()
    private int num;
}
