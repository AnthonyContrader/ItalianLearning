//Created By @Alessandro Alfieri
package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Hangman;

@Repository
@Transactional
public interface HangmanRepository extends CrudRepository<Hangman,Long> {
	
}
