package me.sa1zer_.sporterbook.domain.model.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Basic extensible database storage model for users.
 *
 * @see BaseEntity
 */
@EqualsAndHashCode(callSuper = true)
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

    @Column(columnDefinition = "VARCHAR(24)", nullable = false, unique = true)
    protected String login;

    @Column(columnDefinition = "VARCHAR(64)", nullable = false)
    protected String password;

    @Column(columnDefinition = "smallint DEFAULT 0")
    protected boolean sex;

    @Column(name = "is_active", columnDefinition = "smallint DEFAULT 0")
    protected boolean active;

    @Column(nullable = false)
    private LocalDateTime birth;

    @Column(columnDefinition = "VARCHAR(13)")
    protected String phone;
}
