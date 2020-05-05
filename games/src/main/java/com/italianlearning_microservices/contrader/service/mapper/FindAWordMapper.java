package com.italianlearning_microservices.contrader.service.mapper;

import com.italianlearning_microservices.contrader.domain.*;
import com.italianlearning_microservices.contrader.service.dto.FindAWordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FindAWord and its DTO FindAWordDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, LevelMapper.class})
public interface FindAWordMapper extends EntityMapper<FindAWordDTO, FindAWord> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.title", target = "categoryTitle")
    @Mapping(source = "level.id", target = "levelId")
    @Mapping(source = "level.name", target = "levelName")
    FindAWordDTO toDto(FindAWord findAWord);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "levelId", target = "level")
    FindAWord toEntity(FindAWordDTO findAWordDTO);

    default FindAWord fromId(Long id) {
        if (id == null) {
            return null;
        }
        FindAWord findAWord = new FindAWord();
        findAWord.setId(id);
        return findAWord;
    }
}
