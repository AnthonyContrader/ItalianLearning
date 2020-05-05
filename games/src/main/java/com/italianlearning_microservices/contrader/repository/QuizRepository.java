package com.italianlearning_microservices.contrader.repository;

import com.italianlearning_microservices.contrader.domain.Quiz;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Quiz entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
