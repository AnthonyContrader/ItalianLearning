package com.italianlearning_microservices.contrader.repository;

import com.italianlearning_microservices.contrader.domain.OrganizeSentence;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrganizeSentence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganizeSentenceRepository extends JpaRepository<OrganizeSentence, Long> {

}
