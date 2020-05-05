package com.italianlearning_microservices.contrader.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A FindMistake.
 */
@Entity
@Table(name = "find_mistake")
public class FindMistake implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "solution", nullable = false)
    private String solution;

    @Column(name = "definition")
    private String definition;

    @NotNull
    @Column(name = "sentence", nullable = false)
    private String sentence;

    @NotNull
    @Column(name = "option_a", nullable = false)
    private String optionA;

    @NotNull
    @Column(name = "option_b", nullable = false)
    private String optionB;

    @NotNull
    @Column(name = "option_c", nullable = false)
    private String optionC;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private Category category;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private Level level;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSolution() {
        return solution;
    }

    public FindMistake solution(String solution) {
        this.solution = solution;
        return this;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getDefinition() {
        return definition;
    }

    public FindMistake definition(String definition) {
        this.definition = definition;
        return this;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getSentence() {
        return sentence;
    }

    public FindMistake sentence(String sentence) {
        this.sentence = sentence;
        return this;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getOptionA() {
        return optionA;
    }

    public FindMistake optionA(String optionA) {
        this.optionA = optionA;
        return this;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public FindMistake optionB(String optionB) {
        this.optionB = optionB;
        return this;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public FindMistake optionC(String optionC) {
        this.optionC = optionC;
        return this;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public Category getCategory() {
        return category;
    }

    public FindMistake category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Level getLevel() {
        return level;
    }

    public FindMistake level(Level level) {
        this.level = level;
        return this;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FindMistake findMistake = (FindMistake) o;
        if (findMistake.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), findMistake.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FindMistake{" +
            "id=" + getId() +
            ", solution='" + getSolution() + "'" +
            ", definition='" + getDefinition() + "'" +
            ", sentence='" + getSentence() + "'" +
            ", optionA='" + getOptionA() + "'" +
            ", optionB='" + getOptionB() + "'" +
            ", optionC='" + getOptionC() + "'" +
            "}";
    }
}
