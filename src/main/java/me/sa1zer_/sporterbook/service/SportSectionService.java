package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.payload.handler.ServiceUpdateByRequestHandler;
import me.sa1zer_.sporterbook.payload.request.SportSectionRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SportSectionService extends
        ServiceUpdateByRequestHandler<SportSection, SportSectionRequest> {

    SportSection findById(Long id);

    List<SportSection> findAll();

    List<SportSection> findAll(Pageable pageable);

    SportSection findByName(String name);

    List<SportSection> findAllByPrice(double price);

    List<SportSection> findAllByPriceGreaterThanEqual(double price);

    List<SportSection> findAllByPriceLessThanEqual(double price);

    List<SportSection> findAllByPriceBetween(double min, double max);

    SportSection create(String name, String desc, double prie);

    SportSection save(SportSection sportSection);

    void delete(SportSection section);
}
