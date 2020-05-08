package com.italianlearning_microservices.contrader.service.mapper;

import com.italianlearning_microservices.contrader.domain.*;
import com.italianlearning_microservices.contrader.service.dto.GuessPictureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GuessPicture and its DTO GuessPictureDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, LevelMapper.class})
public interface GuessPictureMapper extends EntityMapper<GuessPictureDTO, GuessPicture> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.title", target = "categoryTitle")
    @Mapping(source = "level.id", target = "levelId")
    @Mapping(source = "level.name", target = "levelName")
    GuessPictureDTO toDto(GuessPicture guessPicture);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "levelId", target = "level")
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
