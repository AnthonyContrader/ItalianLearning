package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.QuizDTO;
import it.contrader.model.Quiz;
import it.contrader.dao.CategoryDAO;
import it.contrader.dao.LevelDAO;

public class QuizConverter implements Converter<Quiz, QuizDTO> {
	
	private CategoryDAO categoryDAO;
	private LevelDAO levelDAO;
	
	
	public QuizConverter() {
	 this.categoryDAO = new CategoryDAO();
	 this.levelDAO = new LevelDAO();
			 
	
	}
	
	
	public QuizDTO toDTO(Quiz quiz) {
		String categoryString = categoryDAO.read(quiz.getIdCategory()).getTitle();
		String levelString = levelDAO.read(quiz.getIdLevel()).getName();
		QuizDTO quizDTO = new QuizDTO(quiz.getId(),quiz.getIdCategory(),quiz.getSolution(),quiz.getDefinition(),categoryString, quiz.getSentence(), quiz.getIdLevel(), levelString );
		return quizDTO;
	}
	
	
	public Quiz toEntity(QuizDTO quizDTO) {
		Quiz quiz = new Quiz(quizDTO.getId(),quizDTO.getIdCategory(),quizDTO.getSolution(),quizDTO.getDefinition(), quizDTO.getSentence(), quizDTO.getIdLevel());
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
