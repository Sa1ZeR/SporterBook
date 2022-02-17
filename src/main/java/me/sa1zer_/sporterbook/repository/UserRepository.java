package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.model.User;
import me.sa1zer_.sporterbook.repository.base.HumanRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface UserRepository extends HumanRepository<User, Long> {
}
