//Created By @Alessandro Alfieri
package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Level;

@Repository
@Transactional
public interface LevelRepository extends CrudRepository<Level, Long> {
	
}
