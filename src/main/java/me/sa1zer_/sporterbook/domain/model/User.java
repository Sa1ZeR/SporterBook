package me.sa1zer_.sporterbook.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import me.sa1zer_.sporterbook.domain.model.enums.Sex;
import me.sa1zer_.sporterbook.domain.model.base.BaseEntity;
import me.sa1zer_.sporterbook.domain.model.enums.Role;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Information about the participant of the section.
 */
@EqualsAndHashCode(of = {"fistName", "LastName", "patronymic", "email", "login",
                "password", "birth", "phone", "created"}, callSuper = true)
@Entity
@Table(name = "api_users")
@RequiredArgsConstructor
@Data
public class User extends BaseEntity {

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

    @Enumerated()
    @Column(columnDefinition = "SMALLINT default 0")
    protected Sex sex;

    @Column(name = "is_active")
    protected boolean active;

    @Column(nullable = false)
    private LocalDateTime birth;

    @Column(columnDefinition = "VARCHAR(13)")
    protected String phone;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name="user_id"))
    @Enumerated
    @Column(name = "role")
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "api_user_children", joinColumns = {@JoinColumn(name = "parent_id")},
            inverseJoinColumns = {@JoinColumn(name = "child_id")})
    private Set<User> children = new HashSet<>();

    private LocalDateTime created;

    @PrePersist
    public void onCreated() {
        created = LocalDateTime.now();
    }
}
