package com.italianlearning_microservices.contrader.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FindMistake entity.
 */
public class FindMistakeDTO implements Serializable {

    private Long id;

    @NotNull
    private String solution;

    private String definition;

    @NotNull
    private String sentence;

    @NotNull
    private String optionA;

    @NotNull
    private String optionB;

    @NotNull
    private String optionC;

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

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
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

        FindMistakeDTO findMistakeDTO = (FindMistakeDTO) o;
        if (findMistakeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), findMistakeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FindMistakeDTO{" +
            "id=" + getId() +
            ", solution='" + getSolution() + "'" +
            ", definition='" + getDefinition() + "'" +
            ", sentence='" + getSentence() + "'" +
            ", optionA='" + getOptionA() + "'" +
            ", optionB='" + getOptionB() + "'" +
            ", optionC='" + getOptionC() + "'" +
            ", category=" + getCategoryId() +
            ", category='" + getCategoryTitle() + "'" +
            ", level=" + getLevelId() +
            ", level='" + getLevelName() + "'" +
            "}";
    }
}
