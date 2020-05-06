package com.italianlearning_microservices.contrader.service.mapper;

import com.italianlearning_microservices.contrader.domain.*;
import com.italianlearning_microservices.contrader.service.dto.OrganizeSentenceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrganizeSentence and its DTO OrganizeSentenceDTO.
 */
@Mapper(componentModel = "spring", uses = {LevelMapper.class, CategoryMapper.class})
public interface OrganizeSentenceMapper extends EntityMapper<OrganizeSentenceDTO, OrganizeSentence> {

    @Mapping(source = "level.id", target = "levelId")
    @Mapping(source = "level.name", target = "levelName")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.title", target = "categoryTitle")
    OrganizeSentenceDTO toDto(OrganizeSentence organizeSentence);

    @Mapping(source = "levelId", target = "level")
    @Mapping(source = "categoryId", target = "category")
    OrganizeSentence toEntity(OrganizeSentenceDTO organizeSentenceDTO);

    default OrganizeSentence fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrganizeSentence organizeSentence = new OrganizeSentence();
        organizeSentence.setId(id);
        return organizeSentence;
    }
}
