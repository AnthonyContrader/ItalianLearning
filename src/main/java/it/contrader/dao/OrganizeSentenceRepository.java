package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.OrganizeSentence;

/*
**created by Torquato Di Maio
*/

@Repository //indichiamo che la classe e un Repository ed abilitiamo le operativita base del CRUD
@Transactional //indica che questa classe e' transazionale cioè quando parla al DB esegue delle transazioni
public interface OrganizeSentenceRepository extends CrudRepository<OrganizeSentence , Long> {
	
	//abbiamo le funzioni prestabilite dal CrudRepository ed inoltre possiamo definire le nostre come nell' UserRepository
	//findbyUsernameAndPassword(String username, String password);

}
