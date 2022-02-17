package me.sa1zer_.sporterbook.repository.base;

import me.sa1zer_.sporterbook.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface HumanRepository<T, ID> extends JpaRepository<T, ID> {

    Role role = Role.UNKNOWN;

    Optional<T> findByLoginOrEmail(String login, String email);
}
