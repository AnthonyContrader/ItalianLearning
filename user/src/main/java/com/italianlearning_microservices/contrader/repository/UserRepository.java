package com.italianlearning_microservices.contrader.repository;

import com.italianlearning_microservices.contrader.domain.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the User entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
