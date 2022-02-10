package me.sa1zer_.sporterbook.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.model.base.BaseHuman;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "api_trainters")
public class Trainer extends BaseHuman {

    private LocalDateTime created;

    @PrePersist
    public void onCreated() {
        created = LocalDateTime.now();
    }
}
