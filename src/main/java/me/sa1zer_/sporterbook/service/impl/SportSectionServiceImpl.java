package me.sa1zer_.sporterbook.service.impl;

import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.exception.SportSectionNotFound;
import me.sa1zer_.sporterbook.repository.SportSectionRepository;
import me.sa1zer_.sporterbook.service.SportSectionService;

import java.util.List;

public class SportSectionServiceImpl implements SportSectionService {

    private final SportSectionRepository sectionRepository;

    public SportSectionServiceImpl(SportSectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public SportSection findById(Long id) {
        return sectionRepository.findById(id).orElseThrow(() ->
                new SportSectionNotFound(String.format(
                        "SportSection with id %s not found", id)));
    }

    @Override
    public List<SportSection> findAll() {
        return sectionRepository.findAll();
    }

    @Override
    public SportSection findByName(String name) {
        return sectionRepository.findByName(name).orElseThrow(() ->
                new SportSectionNotFound(String.format(
                        "SportSection with name %s not found", name)));
    }

    @Override
    public List<SportSection> findAllByPrice(double price) {
        return sectionRepository.findAllByPrice(price);
    }

    @Override
    public List<SportSection> findAllByPriceGreaterThanEqual(double price) {
        return sectionRepository.findAllByPriceGreaterThanEqual(price);
    }

    @Override
    public List<SportSection> findAllByPriceLessThanEqual(double price) {
        return sectionRepository.findAllByPriceLessThanEqual(price);
    }

    @Override
    public List<SportSection> findAllByPriceBetween(double min, double max) {
        return sectionRepository.findAllByPriceBetween(min, max);
    }

    @Override
    public SportSection create(String name, String desc, double prie) {
        SportSection sportSection = new SportSection();

        sportSection.setName(name);
        sportSection.setDesc(desc);
        sportSection.setPrice(prie);

        return save(sportSection);
    }

    @Override
    public SportSection save(SportSection sportSection) {
        return sectionRepository.save(sportSection);
    }

    @Override
    public void delete(SportSection section) {
        sectionRepository.delete(section);
    }
}
