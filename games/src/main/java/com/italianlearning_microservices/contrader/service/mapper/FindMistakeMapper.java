package com.italianlearning_microservices.contrader.service.mapper;

import com.italianlearning_microservices.contrader.domain.*;
import com.italianlearning_microservices.contrader.service.dto.FindMistakeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FindMistake and its DTO FindMistakeDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, LevelMapper.class})
public interface FindMistakeMapper extends EntityMapper<FindMistakeDTO, FindMistake> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.title", target = "categoryTitle")
    @Mapping(source = "level.id", target = "levelId")
    @Mapping(source = "level.name", target = "levelName")
    FindMistakeDTO toDto(FindMistake findMistake);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "levelId", target = "level")
    FindMistake toEntity(FindMistakeDTO findMistakeDTO);

    default FindMistake fromId(Long id) {
        if (id == null) {
            return null;
        }
        FindMistake findMistake = new FindMistake();
        findMistake.setId(id);
        return findMistake;
    }
}
