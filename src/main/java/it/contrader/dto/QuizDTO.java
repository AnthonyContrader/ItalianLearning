package it.contrader.dto;
/*created by Anna Cecere */

import it.contrader.model.Category;
import it.contrader.model.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class QuizDTO {

	private Long id;
	private String solution;
	private String definition;
	private String sentence;
	private CategoryDTO category;
	private LevelDTO level;
}
