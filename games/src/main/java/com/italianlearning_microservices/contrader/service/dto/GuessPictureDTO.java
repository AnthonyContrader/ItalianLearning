package com.italianlearning_microservices.contrader.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the GuessPicture entity.
 */
public class GuessPictureDTO implements Serializable {

    private Long id;

    @NotNull
    private String solution;

    
    @Lob
    private byte[] image;
    private String imageContentType;

    private Long levelId;

    private String levelName;

    private Long categoryId;

    private String categoryTitle;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GuessPictureDTO guessPictureDTO = (GuessPictureDTO) o;
        if (guessPictureDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), guessPictureDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GuessPictureDTO{" +
            "id=" + getId() +
            ", solution='" + getSolution() + "'" +
            ", image='" + getImage() + "'" +
            ", level=" + getLevelId() +
            ", level='" + getLevelName() + "'" +
            ", category=" + getCategoryId() +
            ", category='" + getCategoryTitle() + "'" +
            "}";
    }
}
