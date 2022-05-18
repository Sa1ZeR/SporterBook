package me.sa1zer_.sporterbook.payload.facade;

import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.payload.dto.IUserDto;
import me.sa1zer_.sporterbook.payload.dto.SportSectionDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SportSectionMapper implements Mapper<SportSection, SportSectionDto>{

    private final UserFacade facade;

    public SportSectionMapper(UserFacade facade) {
        this.facade = facade;
    }

    @Override
    public SportSectionDto map(SportSection from) {
        SportSectionDto dto = new SportSectionDto();

        dto.setName(from.getName());
        dto.setDesc(from.getDesc());
        dto.setPrice(dto.getPrice());

        Set<IUserDto> students = from.getStudents().stream().map(facade::map)
                .collect(Collectors.toSet());
        dto.setStudents(students);

        Set<IUserDto> trainers = from.getTrainers().stream().map(facade::map)
                .collect(Collectors.toSet());
        dto.setTrainers(trainers);

        return dto;
    }
}
