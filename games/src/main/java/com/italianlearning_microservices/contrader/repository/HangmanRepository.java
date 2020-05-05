package com.italianlearning_microservices.contrader.repository;

import com.italianlearning_microservices.contrader.domain.Hangman;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Hangman entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HangmanRepository extends JpaRepository<Hangman, Long> {

}
