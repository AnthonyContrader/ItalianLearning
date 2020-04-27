//Created By @Alessandro Alfieri
package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.FindMistake;

@Repository
@Transactional
public interface FindMistakeRepository extends CrudRepository<FindMistake, Long> {

}
