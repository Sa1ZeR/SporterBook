package me.sa1zer_.sporterbook.domain.model;

import lombok.*;
import me.sa1zer_.sporterbook.domain.Sex;
import me.sa1zer_.sporterbook.domain.model.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<UserAttribute> attributes = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_children", joinColumns = {@JoinColumn(name = "parent_id")},
            inverseJoinColumns = {@JoinColumn(name = "child_id")})
    private Set<User> children = new HashSet<>();

    private LocalDateTime created;

    @PrePersist
    public void onCreated() {
        created = LocalDateTime.now();
    }
}
