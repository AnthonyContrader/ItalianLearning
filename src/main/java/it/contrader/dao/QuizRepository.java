package it.contrader.dao;
/* created by Anna Cecere */

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import it.contrader.model.Quiz;


@Repository 
/* 
 * abilita tutte le operazioni CrudReposutary facendole ereditare alla QuizRepository
 */
@Transactional
/* letteralmente "transazione" è una vera e propria operazione di db per eseguire modifiche.
 *  Se qst modifiche sono corrette, le salva, in caso contrario ti blocca.
 */

public interface QuizRepository extends CrudRepository<Quiz, Long> {

	//long è legato all'ID del model
}