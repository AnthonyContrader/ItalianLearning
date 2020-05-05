package com.italianlearning_microservices.contrader.service.mapper;

import com.italianlearning_microservices.contrader.domain.*;
import com.italianlearning_microservices.contrader.service.dto.QuizDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Quiz and its DTO QuizDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, LevelMapper.class})
public interface QuizMapper extends EntityMapper<QuizDTO, Quiz> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.title", target = "categoryTitle")
    @Mapping(source = "level.id", target = "levelId")
    @Mapping(source = "level.name", target = "levelName")
    QuizDTO toDto(Quiz quiz);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "levelId", target = "level")
    Quiz toEntity(QuizDTO quizDTO);

    default Quiz fromId(Long id) {
        if (id == null) {
            return null;
        }
        Quiz quiz = new Quiz();
        quiz.setId(id);
        return quiz;
    }
}
