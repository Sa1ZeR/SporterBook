package me.sa1zer_.sporterbook.payload.dto;

import lombok.Data;

import java.util.Set;

@Data
public class SportSectionDto {

    private Long id;
    private String name;
    private String desc;
    private double price;

    private Set<IUserDto> students;
    private Set<IUserDto> trainers;
}
