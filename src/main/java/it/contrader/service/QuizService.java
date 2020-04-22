package it.contrader.service;
/*created by Anna Cecere */

//import org.springframework.beans.factory.annotation.Autowired;
//import it.contrader.converter.QuizConverter;
//import it.contrader.dao.QuizRepository;
import it.contrader.dto.QuizDTO;
import it.contrader.model.Quiz;
import org.springframework.stereotype.Service;

@Service
public class QuizService extends AbstractService<Quiz, QuizDTO> {
	}

/*
 * queste funzioni noi la ereditiamo dalla public abstract class 
@Autowired
private QuizConverter converter;

@Autowired
private QuizRepository repository;

*@Autowired letteralmente "autolegato"permette di utilizzare le funzionalità speificate nelle crud(repository) e nel coverter.
*/	
	



