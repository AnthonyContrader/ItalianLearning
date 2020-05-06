package com.italianlearning_microservices.contrader.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Category.
 */
@Entity
@Table(name = "categories")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<FindAWord> findAWords = new HashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<FindMistake> findMistakes = new HashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<GuessPicture> guessPictures = new HashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<Hangman> hangmen = new HashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<OrganizeSentence> organizeSentences = new HashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<Quiz> quizzes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Category title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Category description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<FindAWord> getFindAWords() {
        return findAWords;
    }

    public Category findAWords(Set<FindAWord> findAWords) {
        this.findAWords = findAWords;
        return this;
    }

    public Category addFindAWord(FindAWord findAWord) {
        this.findAWords.add(findAWord);
        findAWord.setCategory(this);
        return this;
    }

    public Category removeFindAWord(FindAWord findAWord) {
        this.findAWords.remove(findAWord);
        findAWord.setCategory(null);
        return this;
    }

    public void setFindAWords(Set<FindAWord> findAWords) {
        this.findAWords = findAWords;
    }

    public Set<FindMistake> getFindMistakes() {
        return findMistakes;
    }

    public Category findMistakes(Set<FindMistake> findMistakes) {
        this.findMistakes = findMistakes;
        return this;
    }

    public Category addFindMistake(FindMistake findMistake) {
        this.findMistakes.add(findMistake);
        findMistake.setCategory(this);
        return this;
    }

    public Category removeFindMistake(FindMistake findMistake) {
        this.findMistakes.remove(findMistake);
        findMistake.setCategory(null);
        return this;
    }

    public void setFindMistakes(Set<FindMistake> findMistakes) {
        this.findMistakes = findMistakes;
    }

    public Set<GuessPicture> getGuessPictures() {
        return guessPictures;
    }

    public Category guessPictures(Set<GuessPicture> guessPictures) {
        this.guessPictures = guessPictures;
        return this;
    }

    public Category addGuessPicture(GuessPicture guessPicture) {
        this.guessPictures.add(guessPicture);
        guessPicture.setCategory(this);
        return this;
    }

    public Category removeGuessPicture(GuessPicture guessPicture) {
        this.guessPictures.remove(guessPicture);
        guessPicture.setCategory(null);
        return this;
    }

    public void setGuessPictures(Set<GuessPicture> guessPictures) {
        this.guessPictures = guessPictures;
    }

    public Set<Hangman> getHangmen() {
        return hangmen;
    }

    public Category hangmen(Set<Hangman> hangmen) {
        this.hangmen = hangmen;
        return this;
    }

    public Category addHangman(Hangman hangman) {
        this.hangmen.add(hangman);
        hangman.setCategory(this);
        return this;
    }

    public Category removeHangman(Hangman hangman) {
        this.hangmen.remove(hangman);
        hangman.setCategory(null);
        return this;
    }

    public void setHangmen(Set<Hangman> hangmen) {
        this.hangmen = hangmen;
    }

    public Set<OrganizeSentence> getOrganizeSentences() {
        return organizeSentences;
    }

    public Category organizeSentences(Set<OrganizeSentence> organizeSentences) {
        this.organizeSentences = organizeSentences;
        return this;
    }

    public Category addOrganizeSentence(OrganizeSentence organizeSentence) {
        this.organizeSentences.add(organizeSentence);
        organizeSentence.setCategory(this);
        return this;
    }

    public Category removeOrganizeSentence(OrganizeSentence organizeSentence) {
        this.organizeSentences.remove(organizeSentence);
        organizeSentence.setCategory(null);
        return this;
    }

    public void setOrganizeSentences(Set<OrganizeSentence> organizeSentences) {
        this.organizeSentences = organizeSentences;
    }

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public Category quizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
        return this;
    }

    public Category addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
        quiz.setCategory(this);
        return this;
    }

    public Category removeQuiz(Quiz quiz) {
        this.quizzes.remove(quiz);
        quiz.setCategory(null);
        return this;
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
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
        Category category = (Category) o;
        if (category.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
