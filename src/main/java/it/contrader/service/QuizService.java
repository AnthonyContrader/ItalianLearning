package it.contrader.service;

import java.util.List;
import it.contrader.model.Quiz;
import it.contrader.converter.QuizConverter;
import it.contrader.dao.QuizDAO;
import it.contrader.dto.QuizDTO;


public class QuizService {
	
	private QuizDAO quizDAO;
	private QuizConverter quizConverter;
	
	
	public QuizService() {
		this.quizDAO = new QuizDAO();
		this.quizConverter = new QuizConverter();
	}

	
	public List<QuizDTO> getAll() {
		return quizConverter.toDTOList(quizDAO.getAll());
	}
	public QuizDTO read(int id) {
		Quiz c = quizDAO.read(id);
		if (c != null)
			return quizConverter.toDTO(c);
		else
			return null;
	
		//return quizConverter.toDTO(quizDAO.read(id));
	}
	public boolean insert(QuizDTO dto) {
		return quizDAO.insert(quizConverter.toEntity(dto));
	}
	
	public boolean update(QuizDTO dto) {
		return quizDAO.update(quizConverter.toEntity(dto));
	}
	public boolean delete(int id) {
		return quizDAO.delete(id);
	}
}
