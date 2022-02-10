package me.sa1zer_.sporterbook.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.model.base.BaseEntity;
import me.sa1zer_.sporterbook.model.enums.LogType;
import me.sa1zer_.sporterbook.model.enums.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "api_logs")
@Data
public class Log extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated
    @Column(nullable = false)
    private Role role;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Enumerated()
    private LogType type;
}
