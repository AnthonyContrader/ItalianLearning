package it.contrader.converter;
/* created by Anna Cecere */

import org.springframework.stereotype.Component;

import it.contrader.dto.QuizDTO;
import it.contrader.model.Quiz;

@Component 
/*
classi che possono utilizzare le annotacion nei file precedenti ex. get/setter, costruttori 
*/
 
public class QuizConverter extends AbstractConverter<Quiz, QuizDTO>{
	@Override
	public Quiz toEntity(QuizDTO quizDTO) {
		Quiz quiz = null; //se si genera errore possiamo controllare
		if (quizDTO != null) {
			quiz = new Quiz(quizDTO.getId(), quizDTO.getSolution(), quizDTO.getDefinition(), quizDTO.getSentence(), quizDTO.getCategoryDTO(), quizDTO.getLevelDTO());
		}
		
		return quiz;
	}

	@Override
	public QuizDTO toDTO(Quiz quiz) {
		QuizDTO quizDTO = null;
		if (quiz != null) {
			quiz = new Quiz(quizDTO.getId(), quizDTO.getSolution(), quizDTO.getDefinition(), quizDTO.getSentence(), quizDTO.getCategory(), quizDTO.getLevel());
			}
	
		return quizDTO;
		}
	
}

	