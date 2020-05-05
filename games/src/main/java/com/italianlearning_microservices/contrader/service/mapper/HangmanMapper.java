package com.italianlearning_microservices.contrader.service.mapper;

import com.italianlearning_microservices.contrader.domain.*;
import com.italianlearning_microservices.contrader.service.dto.HangmanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Hangman and its DTO HangmanDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, LevelMapper.class})
public interface HangmanMapper extends EntityMapper<HangmanDTO, Hangman> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.title", target = "categoryTitle")
    @Mapping(source = "level.id", target = "levelId")
    @Mapping(source = "level.name", target = "levelName")
    HangmanDTO toDto(Hangman hangman);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "levelId", target = "level")
    Hangman toEntity(HangmanDTO hangmanDTO);

    default Hangman fromId(Long id) {
        if (id == null) {
            return null;
        }
        Hangman hangman = new Hangman();
        hangman.setId(id);
        return hangman;
    }
}
