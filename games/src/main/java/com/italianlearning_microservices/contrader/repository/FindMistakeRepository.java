package com.italianlearning_microservices.contrader.repository;

import com.italianlearning_microservices.contrader.domain.FindMistake;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FindMistake entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FindMistakeRepository extends JpaRepository<FindMistake, Long> {

}
