//Created By @Alessandro Alfieri
package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindMistakeDTO {
	
	private static final String typeGame = "FindMistake";
		
	private Long id;
	
	private String solution;
	
	private String definition;
	
	private String sentence;
	
	private String optionA;
	
	private String optionB;
	
	private String optionC;
	
	private CategoryDTO category;
	
	private LevelDTO level;
	
	public static String getTypeGame() {
		return typeGame;
	}
}
