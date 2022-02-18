package me.sa1zer_.sporterbook.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.model.base.BaseEntity;
import me.sa1zer_.sporterbook.model.enums.LogType;
import me.sa1zer_.sporterbook.model.enums.Role;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "api_logs")
@Data
public class Log extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Enumerated
    @Column(nullable = false)
    private Role role;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Enumerated
    private LogType type;
}
