package it.contrader.dto;
/*created by Anna Cecere */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class QuizDTO {

	private static final String typeGame = "Quiz";
	
	private Long id;
	private String solution;
	private String definition;
	private String sentence;
	private CategoryDTO category;
	private LevelDTO level;
	
	public static String getTypeGame() {
		return typeGame;
	}
}
