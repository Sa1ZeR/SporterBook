package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.model.Student;
import me.sa1zer_.sporterbook.repository.base.HumanRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends HumanRepository<Student, Long> {
}
