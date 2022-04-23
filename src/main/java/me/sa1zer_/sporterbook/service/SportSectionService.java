package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.SportSection;

import java.util.List;

public interface SportSectionService {

    SportSection findById(Long id);

    List<SportSection> findAll();

    SportSection findByName(String name);

    List<SportSection> findAllByPrice(double price);

    List<SportSection> findAllByPriceGreaterThanEqual(double price);

    List<SportSection> findAllByPriceLessThanEqual(double price);

    List<SportSection> findAllByPriceBetween(double min, double max);

    SportSection create(String name, String desc, double prie);

    SportSection save(SportSection sportSection);

    void delete(SportSection section);
}
