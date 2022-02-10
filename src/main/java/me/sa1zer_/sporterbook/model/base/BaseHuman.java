package me.sa1zer_.sporterbook.model.base;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class BaseHuman extends BaseEntity {

    @Column(name = "first_name", columnDefinition = "VARCHAR(48)", nullable = false)
    protected String fistName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(48)", nullable = false)
    protected String LastName;

    @Column(columnDefinition = "VARCHAR(48)", nullable = false)
    protected String patronymic;

    @Column(columnDefinition = "VARCHAR(64)", nullable = false, unique = true)
    protected String email;

    @Column(columnDefinition = "smallint DEFAULT 0")
    protected boolean sex;

    @Column(columnDefinition = "VARCHAR(13)")
    protected String phone;
}
