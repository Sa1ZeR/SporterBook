package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.Parent;
import me.sa1zer_.sporterbook.domain.model.enums.Role;
import me.sa1zer_.sporterbook.repository.base.HumanRepository;
import org.springframework.stereotype.Repository;

@Repository()
@Deprecated
public interface ParentRepository extends HumanRepository<Parent, Long> {

    Role role = Role.PARENT;
}
