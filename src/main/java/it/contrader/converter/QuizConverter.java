package it.contrader.converter;
/* created by Anna Cecere */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.contrader.dto.QuizDTO;
import it.contrader.model.Quiz;

@Component 
/*
classi che possono utilizzare le annotacion nei file precedenti ex. get/setter, costruttori 
*/
 
public class QuizConverter extends AbstractConverter<Quiz, QuizDTO>{
	

	@Autowired
	CategoryConverter categoryConverter = new CategoryConverter();
	@Autowired
	LevelConverter levelConverter = new LevelConverter();
	
	@Override
	public Quiz toEntity(QuizDTO quizDTO) {
		Quiz quiz = null; //se si genera errore possiamo controllare
		if (quizDTO != null) {
			quiz = new Quiz(quizDTO.getId(), quizDTO.getSolution(), quizDTO.getDefinition(), quizDTO.getSentence(), categoryConverter.toEntity(quizDTO.getCategory()), levelConverter.toEntity(quizDTO.getLevel()));
		}
		
		return quiz;
	}

	@Override
	public QuizDTO toDTO(Quiz quiz) {
		QuizDTO quizDTO = null;
		if (quiz != null) {
			quizDTO = new QuizDTO(quiz.getId(), quiz.getSolution(),quiz.getDefinition(), quiz.getSentence(), categoryConverter.toDTO(quiz.getCategory()), levelConverter.toDTO(quiz.getLevel()));
			}
//abbiamo fatto conversione dei due oggetti level e category per oterli poi utilizzare nel controller
		return quizDTO;
		}
	
}

	