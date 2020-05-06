package com.italianlearning_microservices.contrader.service.mapper;

import com.italianlearning_microservices.contrader.domain.*;
import com.italianlearning_microservices.contrader.service.dto.GuessPictureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GuessPicture and its DTO GuessPictureDTO.
 */
@Mapper(componentModel = "spring", uses = {LevelMapper.class, CategoryMapper.class})
public interface GuessPictureMapper extends EntityMapper<GuessPictureDTO, GuessPicture> {

    @Mapping(source = "level.id", target = "levelId")
    @Mapping(source = "level.name", target = "levelName")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.title", target = "categoryTitle")
    GuessPictureDTO toDto(GuessPicture guessPicture);

    @Mapping(source = "levelId", target = "level")
    @Mapping(source = "categoryId", target = "category")
    GuessPicture toEntity(GuessPictureDTO guessPictureDTO);

    default GuessPicture fromId(Long id) {
        if (id == null) {
            return null;
        }
        GuessPicture guessPicture = new GuessPicture();
        guessPicture.setId(id);
        return guessPicture;
    }
}
