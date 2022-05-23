package me.sa1zer_.sporterbook.domain.model.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Basic Extensible Identifier Entity
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(of = {"id"})
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}
