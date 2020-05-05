package com.italianlearning_microservices.contrader.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FindAWord entity.
 */
public class FindAWordDTO implements Serializable {

    private Long id;

    @NotNull
    private String solution;

    private String definition;

    @NotNull
    private String sentence;

    private Long categoryId;

    private String categoryTitle;

    private Long levelId;

    private String levelName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FindAWordDTO findAWordDTO = (FindAWordDTO) o;
        if (findAWordDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), findAWordDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FindAWordDTO{" +
            "id=" + getId() +
            ", solution='" + getSolution() + "'" +
            ", definition='" + getDefinition() + "'" +
            ", sentence='" + getSentence() + "'" +
            ", category=" + getCategoryId() +
            ", category='" + getCategoryTitle() + "'" +
            ", level=" + getLevelId() +
            ", level='" + getLevelName() + "'" +
            "}";
    }
}
