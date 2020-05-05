package com.italianlearning_microservices.contrader.repository;

import com.italianlearning_microservices.contrader.domain.GuessPicture;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GuessPicture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GuessPictureRepository extends JpaRepository<GuessPicture, Long> {

}
