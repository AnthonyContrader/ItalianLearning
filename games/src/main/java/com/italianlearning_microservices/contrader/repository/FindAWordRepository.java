package com.italianlearning_microservices.contrader.repository;

import com.italianlearning_microservices.contrader.domain.FindAWord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FindAWord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FindAWordRepository extends JpaRepository<FindAWord, Long> {

}
