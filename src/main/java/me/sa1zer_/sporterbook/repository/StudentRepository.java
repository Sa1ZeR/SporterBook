package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.Student;
import me.sa1zer_.sporterbook.repository.base.HumanRepository;
import org.springframework.stereotype.Repository;

@Repository
@Deprecated
public interface StudentRepository extends HumanRepository<Student, Long> {
}
