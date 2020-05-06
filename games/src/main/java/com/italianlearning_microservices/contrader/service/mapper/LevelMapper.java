package com.italianlearning_microservices.contrader.service.mapper;

import com.italianlearning_microservices.contrader.domain.*;
import com.italianlearning_microservices.contrader.service.dto.LevelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Level and its DTO LevelDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LevelMapper extends EntityMapper<LevelDTO, Level> {


    @Mapping(target = "findAWords", ignore = true)
    @Mapping(target = "findMistakes", ignore = true)
    @Mapping(target = "guessPictures", ignore = true)
    @Mapping(target = "hangmen", ignore = true)
    @Mapping(target = "organizeSentences", ignore = true)
    @Mapping(target = "quizzes", ignore = true)
    Level toEntity(LevelDTO levelDTO);

    default Level fromId(Long id) {
        if (id == null) {
            return null;
        }
        Level level = new Level();
        level.setId(id);
        return level;
    }
}
