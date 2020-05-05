package com.italianlearning_microservices.contrader.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A OrganizeSentence.
 */
@Entity
@Table(name = "organize_sentence")
public class OrganizeSentence implements Serializable {

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

    @ManyToOne
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

    public OrganizeSentence solution(String solution) {
        this.solution = solution;
        return this;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getDefinition() {
        return definition;
    }

    public OrganizeSentence definition(String definition) {
        this.definition = definition;
        return this;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getSentence() {
        return sentence;
    }

    public OrganizeSentence sentence(String sentence) {
        this.sentence = sentence;
        return this;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public Category getCategory() {
        return category;
    }

    public OrganizeSentence category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Level getLevel() {
        return level;
    }

    public OrganizeSentence level(Level level) {
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
        OrganizeSentence organizeSentence = (OrganizeSentence) o;
        if (organizeSentence.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), organizeSentence.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrganizeSentence{" +
            "id=" + getId() +
            ", solution='" + getSolution() + "'" +
            ", definition='" + getDefinition() + "'" +
            ", sentence='" + getSentence() + "'" +
            "}";
    }
}
