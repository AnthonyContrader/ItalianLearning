package it.contrader.service;

import it.contrader.converter.QuizConverter;
import it.contrader.dao.QuizDAO;
import it.contrader.dto.QuizDTO;
import it.contrader.model.Quiz;


public class QuizService extends AbstractService<Quiz, QuizDTO> {
	
	
	
	public QuizService() {
		this.dao = new QuizDAO();
		this.converter  = new QuizConverter();
	}

}
