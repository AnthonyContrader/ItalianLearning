///Created By @Alessandro Alfieri
package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HangmanDTO {
	
private Long id;
	
	private String solution;
	
	private String definition;
	
	private String sentence;
	
	private CategoryDTO category;
	
	private LevelDTO level;
}
