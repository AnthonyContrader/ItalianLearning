package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.QuizDTO;
import it.contrader.model.Quiz;
import it.contrader.dao.CategoryDAO;

public class QuizConverter implements Converter<Quiz, QuizDTO> {
	
	private CategoryDAO categoryDAO;
	
	public QuizConverter() {
	 this.categoryDAO = new CategoryDAO();
	
	}
	
	
	public QuizDTO toDTO(Quiz quiz) {
		String categoryString = categoryDAO.read(quiz.getIdCategory()).getTitle();
		QuizDTO quizDTO = new QuizDTO(quiz.getId(),quiz.getIdCategory(),quiz.getScore(), quiz.getSolution(),quiz.getDefinition(),categoryString, quiz.getSentence() );
		return quizDTO;
	}
	
	
	public Quiz toEntity(QuizDTO quizDTO) {
		Quiz quiz = new Quiz(quizDTO.getId(),quizDTO.getIdCategory(),quizDTO.getScore(), quizDTO.getSolution(),quizDTO.getDefinition(), quizDTO.getSentence());
		return quiz;
	}
	

	public List<QuizDTO> toDTOList(List<Quiz> quizList){
		
		
		List<QuizDTO> quizDTOList = new ArrayList<QuizDTO>();
		
		
		for (Quiz quiz : quizList) {
			quizDTOList.add(toDTO(quiz));
		}
		return quizDTOList;

	}
}
