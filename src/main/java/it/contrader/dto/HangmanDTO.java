///Created By @Alessandro Alfieri
package it.contrader.dto;

import it.contrader.model.Category;
import it.contrader.model.Level;
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
	
	private Category category;
	
	private Level level;
}
