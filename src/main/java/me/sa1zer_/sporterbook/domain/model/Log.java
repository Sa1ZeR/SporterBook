package me.sa1zer_.sporterbook.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.domain.model.base.BaseEntity;
import me.sa1zer_.sporterbook.domain.model.enums.LogType;
import me.sa1zer_.sporterbook.domain.model.enums.Role;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "api_logs")
@Data
public class Log extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Enumerated
    private LogType type;

    private LocalDateTime created;

    @PrePersist
    public void onCreate() {
        created = LocalDateTime.now();
    }

}
