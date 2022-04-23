package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.SportSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SportSectionRepository extends JpaRepository<SportSection, Long> {

    Optional<SportSection> findByName(String name);

    List<SportSection> findAllByPrice(double price);

    List<SportSection> findAllByPriceGreaterThanEqual(double price);

    List<SportSection> findAllByPriceLessThanEqual(double price);

    List<SportSection> findAllByPriceBetween(double min, double max);
}
