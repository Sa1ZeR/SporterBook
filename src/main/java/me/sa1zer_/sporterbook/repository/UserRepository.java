package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginOrEmail(String login, String email);

}
