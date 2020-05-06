package com.italianlearning_microservices.contrader.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Level.
 */
@Entity
@Table(name = "level")
public class Level implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Min(value = 1)
    @Column(name = "score", nullable = false)
    private Integer score;

    @OneToMany(mappedBy = "level")
    private Set<FindAWord> findAWords = new HashSet<>();

    @OneToMany(mappedBy = "level")
    private Set<FindMistake> findMistakes = new HashSet<>();

    @OneToMany(mappedBy = "level")
    private Set<GuessPicture> guessPictures = new HashSet<>();

    @OneToMany(mappedBy = "level")
    private Set<Hangman> hangmen = new HashSet<>();

    @OneToMany(mappedBy = "level")
    private Set<OrganizeSentence> organizeSentences = new HashSet<>();

    @OneToMany(mappedBy = "level")
    private Set<Quiz> quizzes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Level name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Level description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public Level score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Set<FindAWord> getFindAWords() {
        return findAWords;
    }

    public Level findAWords(Set<FindAWord> findAWords) {
        this.findAWords = findAWords;
        return this;
    }

    public Level addFindAWord(FindAWord findAWord) {
        this.findAWords.add(findAWord);
        findAWord.setLevel(this);
        return this;
    }

    public Level removeFindAWord(FindAWord findAWord) {
        this.findAWords.remove(findAWord);
        findAWord.setLevel(null);
        return this;
    }

    public void setFindAWords(Set<FindAWord> findAWords) {
        this.findAWords = findAWords;
    }

    public Set<FindMistake> getFindMistakes() {
        return findMistakes;
    }

    public Level findMistakes(Set<FindMistake> findMistakes) {
        this.findMistakes = findMistakes;
        return this;
    }

    public Level addFindMistake(FindMistake findMistake) {
        this.findMistakes.add(findMistake);
        findMistake.setLevel(this);
        return this;
    }

    public Level removeFindMistake(FindMistake findMistake) {
        this.findMistakes.remove(findMistake);
        findMistake.setLevel(null);
        return this;
    }

    public void setFindMistakes(Set<FindMistake> findMistakes) {
        this.findMistakes = findMistakes;
    }

    public Set<GuessPicture> getGuessPictures() {
        return guessPictures;
    }

    public Level guessPictures(Set<GuessPicture> guessPictures) {
        this.guessPictures = guessPictures;
        return this;
    }

    public Level addGuessPicture(GuessPicture guessPicture) {
        this.guessPictures.add(guessPicture);
        guessPicture.setLevel(this);
        return this;
    }

    public Level removeGuessPicture(GuessPicture guessPicture) {
        this.guessPictures.remove(guessPicture);
        guessPicture.setLevel(null);
        return this;
    }

    public void setGuessPictures(Set<GuessPicture> guessPictures) {
        this.guessPictures = guessPictures;
    }

    public Set<Hangman> getHangmen() {
        return hangmen;
    }

    public Level hangmen(Set<Hangman> hangmen) {
        this.hangmen = hangmen;
        return this;
    }

    public Level addHangman(Hangman hangman) {
        this.hangmen.add(hangman);
        hangman.setLevel(this);
        return this;
    }

    public Level removeHangman(Hangman hangman) {
        this.hangmen.remove(hangman);
        hangman.setLevel(null);
        return this;
    }

    public void setHangmen(Set<Hangman> hangmen) {
        this.hangmen = hangmen;
    }

    public Set<OrganizeSentence> getOrganizeSentences() {
        return organizeSentences;
    }

    public Level organizeSentences(Set<OrganizeSentence> organizeSentences) {
        this.organizeSentences = organizeSentences;
        return this;
    }

    public Level addOrganizeSentence(OrganizeSentence organizeSentence) {
        this.organizeSentences.add(organizeSentence);
        organizeSentence.setLevel(this);
        return this;
    }

    public Level removeOrganizeSentence(OrganizeSentence organizeSentence) {
        this.organizeSentences.remove(organizeSentence);
        organizeSentence.setLevel(null);
        return this;
    }

    public void setOrganizeSentences(Set<OrganizeSentence> organizeSentences) {
        this.organizeSentences = organizeSentences;
    }

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public Level quizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
        return this;
    }

    public Level addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
        quiz.setLevel(this);
        return this;
    }

    public Level removeQuiz(Quiz quiz) {
        this.quizzes.remove(quiz);
        quiz.setLevel(null);
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
        Level level = (Level) o;
        if (level.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), level.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Level{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", score=" + getScore() +
            "}";
    }
}
