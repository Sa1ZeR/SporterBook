package me.sa1zer_.sporterbook.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.domain.model.base.BaseEntity;
import me.sa1zer_.sporterbook.domain.model.enums.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Table(name = "api_user_attributes")
@Entity
@Data
public class UserAttribute extends BaseEntity {

    @Column(name = "attr_id")
    private Long atrId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated
    @Column(nullable = false)
    private Role role;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<TimeTableInfo> timeTableInfo = new HashSet<>();
}
