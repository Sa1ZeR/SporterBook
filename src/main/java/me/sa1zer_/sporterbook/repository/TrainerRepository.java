package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.Trainer;
import me.sa1zer_.sporterbook.repository.base.HumanRepository;
import org.springframework.stereotype.Repository;

@Repository
@Deprecated
public interface TrainerRepository extends HumanRepository<Trainer, Long> {
}
