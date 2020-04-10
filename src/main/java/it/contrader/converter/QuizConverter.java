package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.QuizDTO;
import it.contrader.model.Quiz;

public class QuizConverter {
	public QuizDTO toDTO(Quiz quiz) {
		QuizDTO quizDTO = new QuizDTO(quiz.getId(),quiz.getIdCategory(),quiz.getScore(), quiz.getSolution(),quiz.getDefinition());
		return quizDTO;
	}
	
	
	public Quiz toEntity(QuizDTO quizDTO) {
		Quiz quiz = new Quiz(quizDTO.getSolution(),quizDTO.getDefinition(),quizDTO.getId(), quizDTO.getIdCategory(),quizDTO.getScore());
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
