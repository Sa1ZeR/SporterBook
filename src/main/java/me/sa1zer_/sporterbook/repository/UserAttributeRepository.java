package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.UserAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAttributeRepository extends JpaRepository<UserAttribute, Long> {
}
